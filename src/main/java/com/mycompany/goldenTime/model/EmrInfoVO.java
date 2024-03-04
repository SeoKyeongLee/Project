package com.mycompany.goldenTime.model;

import org.springframework.stereotype.Component;

@Component
public class EmrInfoVO {
	
	private String emrArea;
	private String dutyName;
	private String dutyAddr;
	private String dutyTel3;
	
	public EmrInfoVO() {
		
	}
	public EmrInfoVO(String dutyName,String dutyAddr,String dutyTel3) {
		this.dutyName=dutyName;
		this.dutyAddr=dutyAddr;
		this.dutyTel3=dutyTel3;
	}
	public String getEmrArea() {
		return emrArea;
	}
	public void setEmrArea(String emrArea) {
		this.emrArea = emrArea;
	}
	public String getDutyName() {
		return dutyName;
	}
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}
	public String getDutyAddr() {
		return dutyAddr;
	}
	public void setDutyAddr(String dutyAddr) {
		this.dutyAddr = dutyAddr;
	}
	public String getDutyTel3() {
		return dutyTel3;
	}
	public void setDutyTel3(String dutyTel3) {
		this.dutyTel3 = dutyTel3;
	}
}
