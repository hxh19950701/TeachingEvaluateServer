package com.hxh19950701.comm;

import java.sql.Timestamp;

import com.googlecode.jsonplugin.annotations.JSON;

public class TimeMakableRecord extends StringMakableRecord {

	private Timestamp createTime;
	private Timestamp updateTime;

	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public final Timestamp getCreateTime() {
		return createTime;
	}

	public final void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public final Timestamp getUpdateTime() {
		return updateTime;
	}

	public final void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public final void notifyCreated() {
		if (createTime == null) {
			createTime = new Timestamp(System.currentTimeMillis());
			notifyUpdated();
		}
	}

	public final void notifyUpdated() {
		if(createTime == null){
			createTime = new Timestamp(System.currentTimeMillis());
		}
		updateTime = new Timestamp(System.currentTimeMillis());
	}
}
