package com.hxh19950701.pojos;

import com.googlecode.jsonplugin.annotations.JSON;
import com.hxh19950701.comm.TimeMakableRecord;

public class User extends TimeMakableRecord {

	private String username;
	private String password;
	private int identity;
	private int createType;
	private boolean enable;

	public User() {

	}

	public User(String username, String password, int identity, int createType) {
		this.username = username;
		this.password = password;
		this.identity = identity;
		this.createType = createType;
		this.enable = true;				//默认启用
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JSON(serialize = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIdentity() {
		return identity;
	}

	public void setIdentity(int identity) {
		this.identity = identity;
	}

	public int getCreateType() {
		return createType;
	}

	public void setCreateType(int createType) {
		this.createType = createType;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
