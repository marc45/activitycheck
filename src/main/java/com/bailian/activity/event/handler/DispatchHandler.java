package com.bailian.activity.event.handler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bailian.activity.check.entity.CheckTemplate;
import com.bailian.activity.check.service.ConfigManager;
import com.bailian.activity.event.CheckEvent;
import com.lmax.disruptor.EventHandler;

public class DispatchHandler implements EventHandler<CheckEvent>{

	private ConfigManager configManager;
	
	private final int outputIndex=1;
	
	@Override
	public void onEvent(CheckEvent event, long arg1, boolean arg2) throws Exception {
		// TODO Auto-generated method stub
		//根据主检查配置，获取子任务模板
		List<CheckTemplate> templateList = configManager.getSubCheck(event.getCheckCode());
		//根据channel分组,例如促销，商品，库存，搜索;以便在服务通道上控制
		Map<String,List<CheckTemplate>> ct = templateList.stream().collect(Collectors.groupingBy(CheckTemplate::getChannel));
		event.setOutput(outputIndex,ct);		
	}
	
	

}
