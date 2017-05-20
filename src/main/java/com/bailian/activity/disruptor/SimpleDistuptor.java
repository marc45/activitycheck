package com.bailian.activity.disruptor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import com.bailian.activity.event.CheckEvent;
import com.bailian.activity.event.CheckEventFactory;
import com.bailian.activity.event.handler.RequestHandler;
import com.bailian.activity.event.input.CheckEventProducer;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class SimpleDistuptor implements InitializingBean,DisposableBean{

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleDistuptor.class);
	
	private Disruptor<CheckEvent> disruptor;
	
	@Value("${ringbuffer.size}")
	private int defineBuffersize;
	
	private RequestHandler requestHandler;
	
	private OutputHandler outputHandler;
	
	private CheckEventProducer producer;
	
	@Override
	public void destroy() throws Exception {
		disruptor.shutdown();
		
	}
	
	/**
	 * 接收的检查请求全部做异步处理
	 * 单生产者
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		ThreadFactory threadFactroy = Executors.defaultThreadFactory();
		CheckEventFactory eventFactory = new CheckEventFactory();
		disruptor = new Disruptor(eventFactory,defineBuffersize,threadFactroy,ProducerType.SINGLE,new BlockingWaitStrategy());
		disruptor.handleEventsWith(requestHandler).then(outputHandler);
		disruptor.start();
		RingBuffer<CheckEvent> ringBuffer = disruptor.getRingBuffer();
		producer = new CheckEventProducer(ringBuffer);
	}
	
	/**
	 * HTTP 请求入口
	 * @param jsonParam
	 */
	public void dispatchRequest(String jsonParam){
		producer.onData(jsonParam);
	}

	/**
	 * JMS 消息入口
	 * @param msg
	 */
	public void onMessage(Message msg){
		if (msg!=null){
			String m = transferMsg(msg);
			if (StringUtils.isEmpty(m)){
				return;
			}
			onMessage(m);	
		}
	}

	private void onMessage(String m) {
		producer.onData(m);		
	}

	private String transferMsg(Message message) {
		String textMessage = "";
		if (message instanceof TextMessage) {
			try {
				textMessage = ((TextMessage) message).getText();
			} catch (JMSException e) {
				LOGGER.error("exception  occur when transfer message to text...",e);
			}
		}
		if (message instanceof BytesMessage) {
			try {
				BytesMessage tmpMessage = (BytesMessage)message;
				byte[] bys = new byte[(int) tmpMessage.getBodyLength()];  
				tmpMessage.readBytes(bys);  
				textMessage = new String(bys,"UTF-8");
			} catch (Exception e) {
				LOGGER.error("exception occur when transfer message to text...",e);
			}
		}
		return textMessage;
	}
	
}
