package com.bailian.activity.event;

import com.lmax.disruptor.EventFactory;

public class CheckEventFactory implements EventFactory<CheckEvent>{

	@Override
	public CheckEvent newInstance() {
		return new CheckEvent(5);
	}

}
