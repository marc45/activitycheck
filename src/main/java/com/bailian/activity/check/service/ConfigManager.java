package com.bailian.activity.check.service;

import java.util.List;

import com.bailian.activity.check.entity.CheckTemplate;

public interface ConfigManager {
	
	/**
	 * 根据检查码获取对应配置的自模板
	 * @param checkCode
	 * @return
	 */
	List<CheckTemplate> getSubCheck(String checkCode);

}
