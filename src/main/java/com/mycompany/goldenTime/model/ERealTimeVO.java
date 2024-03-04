package com.mycompany.goldenTime.model;

import org.springframework.stereotype.Component;

@Component
public class ERealTimeVO {
	
	private String name;
	private int hvgc;
	private int hvoc;
	private int hperyn;
	private int hvec;
	private String emReceive3;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHvgc() {
		return hvgc;
	}

	public void setHvgc(int hvgc) {
		this.hvgc = hvgc;
	}

	public int getHvoc() {
		return hvoc;
	}

	public void setHvoc(int hvoc) {
		this.hvoc = hvoc;
	}

	public int getHperyn() {
		return hperyn;
	}

	public void setHperyn(int hperyn) {
		this.hperyn = hperyn;
	}

	public int getHvec() {
		return hvec;
	}

	public void setHvec(int hvec) {
		this.hvec = hvec;
	}

	public String getEmReceive3() {
		return emReceive3;
	}

	public void setEmReceive3(String emReceive3) {
		this.emReceive3 = emReceive3;
	}
	
}
