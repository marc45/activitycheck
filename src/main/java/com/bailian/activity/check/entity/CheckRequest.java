package com.bailian.activity.check.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CheckRequest implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	
	private String checkChannel;//需要检查的通道（系统），位规则
	
	private String checkCode;//检查编码
	
	private String checkStatus;//检查状态
	
	private Date requestDate;
	
	private String user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCheckChannel() {
		return checkChannel;
	}

	public void setCheckChannel(String checkChannel) {
		this.checkChannel = checkChannel;
	}
	
	
	
}
