package com.bailian.activity.event.handler;

import com.bailian.activity.event.CheckEvent;
import com.lmax.disruptor.EventHandler;

public class OutputHandler implements EventHandler<CheckEvent> {

	
	
	@Override
	public void onEvent(CheckEvent checkEvent, long arg1, boolean arg2) throws Exception {
		// 
		// 寻找子任务生产者，在子任务管道传输
		Producer producer = ProducerFactory.create();
		
	}

}
