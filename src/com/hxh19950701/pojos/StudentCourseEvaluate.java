package com.hxh19950701.pojos;

import com.googlecode.jsonplugin.annotations.JSON;
import com.hxh19950701.comm.TimeMakableRecord;

public class StudentCourseEvaluate extends TimeMakableRecord {

	private StudentCourseInfo info;
	private EvaluateThirdTarget item;
	private float score;

	public StudentCourseEvaluate() {
		// TODO Auto-generated constructor stub
	}

	public StudentCourseEvaluate(StudentCourseInfo info, EvaluateThirdTarget item, float score) {
		this.info = info;
		this.item = item;
		this.score = score;
	}

	@JSON(serialize = false)
	public StudentCourseInfo getInfo() {
		return info;
	}

	public void setInfo(StudentCourseInfo info) {
		this.info = info;
	}

	public EvaluateThirdTarget getItem() {
		return item;
	}

	public void setItem(EvaluateThirdTarget item) {
		this.item = item;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

}
