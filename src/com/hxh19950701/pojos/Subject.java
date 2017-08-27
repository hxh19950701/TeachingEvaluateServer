package com.hxh19950701.pojos;

import com.hxh19950701.comm.TimeMakableRecord;

public class Subject extends TimeMakableRecord {

	private Department department;
	private String name;
	
	public Subject(){
	}
	
	public Subject(Department department, String name) {
		this.department = department;
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
