package com.bailian.activity.event.input;

import com.bailian.activity.event.CheckEvent;
import com.lmax.disruptor.RingBuffer;

public class CheckEventProducer {
	private final RingBuffer<CheckEvent> ringBuffer;
	
	public CheckEventProducer(RingBuffer<CheckEvent> ringBuffer){
		this.ringBuffer = ringBuffer;
	}
	
	public void onData(String msg){
		long sequence = ringBuffer.next();
		try{
			CheckEvent checkEvent = ringBuffer.get(sequence);//从下一个卡槽拿存储
			transfer(checkEvent,msg);
		}finally{
			ringBuffer.publish(sequence);//必须要发布卡槽，无论是否有异常
		}
		
	}

	private void transfer(CheckEvent checkEvent, String msg) {
		// TODO Auto-generated method stub
		
	}
	
}
