package com.bailian.activity.event;

public class CheckEvent {
	
	private String requestId;//请求id
	private String checkCode;//检查编码
	private String checkChannel;//检查的系统通道
	private String param;//检查参数
	private Object[] output;//输出参数，每个处理的参数保留在这个数组里
	public CheckEvent(int i) {
		output = new Object[i];
	}
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public String getCheckChannel() {
		return checkChannel;
	}
	public void setCheckChannel(String checkChannel) {
		this.checkChannel = checkChannel;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public Object[] getOutput() {
		return output;
	}
	public void setOutput(Object[] output) {
		this.output = output;
	}
	
	
	
	
	
	
}
