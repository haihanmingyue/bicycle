package com.aowin.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class MsPhase {
	/* @NotNull (groups = Update.class) */
	private Integer phaseId;
	
	//url 以/开头的只能有字母数字组成 最大长度200
	@Pattern(regexp = "/[a-zA-Z\\d]{1,199}")
	@NotEmpty
	private String url;
	//描述 中文汉字 最多50个字符
	@Pattern(regexp = "[\u4e00-\u9fa5]{1,50}")
	@NotEmpty
	private String description;
	
	public Integer getPhaseId() {
		return phaseId;
	}
	public void setPhaseId(Integer phaseId) {
		this.phaseId = phaseId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
