package com.hxh19950701.pojos;

import com.hxh19950701.comm.TimeMakableRecord;

public class EvaluateSecondTarget extends TimeMakableRecord {

	private String name;
	private EvaluateFirstTarget firstTarget;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EvaluateFirstTarget getFirstTarget() {
		return firstTarget;
	}

	public void setFirstTarget(EvaluateFirstTarget firstTarget) {
		this.firstTarget = firstTarget;
	}
}
