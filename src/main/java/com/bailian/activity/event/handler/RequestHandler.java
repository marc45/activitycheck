package com.bailian.activity.event.handler;

import org.springframework.beans.BeanUtils;

import com.bailian.activity.check.entity.CheckRequest;
import com.bailian.activity.check.repository.CheckRequestRepository;
import com.bailian.activity.event.CheckEvent;
import com.lmax.disruptor.EventHandler;

public class RequestHandler implements EventHandler<CheckEvent>{

	private CheckRequestRepository checkRequestRepository;
	
	@Override
	public void onEvent(CheckEvent checkEvent, long arg1, boolean arg2) throws Exception {
		CheckRequest request = new CheckRequest();
		BeanUtils.copyProperties(checkEvent, request);
		checkRequestRepository.save(request);
	}

	
	
}
