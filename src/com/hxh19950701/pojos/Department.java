package com.hxh19950701.pojos;

import com.hxh19950701.comm.TimeMakableRecord;

public class Department extends TimeMakableRecord {

	private String name;
	
	public Department(){
	}
	
	public Department(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}