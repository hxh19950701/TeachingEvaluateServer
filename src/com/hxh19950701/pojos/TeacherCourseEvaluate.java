package com.hxh19950701.pojos;

import com.hxh19950701.comm.TimeMakableRecord;

public class TeacherCourseEvaluate extends TimeMakableRecord {

	private Course course;
	private EvaluateThirdTarget item;
	private float score;

	public TeacherCourseEvaluate(){
		
	}
	
	public TeacherCourseEvaluate(Course course, EvaluateThirdTarget item){
		this.course = course;
		this.item = item;
		score = 0.0f;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
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
