package com.bailian.activity.event.handler;

import java.util.List;

import com.bailian.activity.check.entity.CheckTemplate;
import com.bailian.activity.event.CheckEvent;
import com.lmax.disruptor.EventHandler;

public class DispatchHandler implements EventHandler<CheckEvent>{

	private ConfigMamager configManager;
	
	@Override
	public void onEvent(CheckEvent event, long arg1, boolean arg2) throws Exception {
		// TODO Auto-generated method stub
		//根据主检查配置，获取子任务分类，放入输出卡槽
		List<CheckTemplate> templateList = configManager.getSub(event.getCheckCode());
		
		
		
		
	}
	
	

}
