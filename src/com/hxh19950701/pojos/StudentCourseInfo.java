package com.hxh19950701.pojos;

import java.sql.Timestamp;

import com.googlecode.jsonplugin.annotations.JSON;
import com.hxh19950701.comm.StringMakableRecord;

/**
 * 这张表存放了学生对于课程的评价信息
 */
public class StudentCourseInfo extends StringMakableRecord {

	private Student student;//学生
	private Course course;//课程
	private int score;// ѧ��Ըÿγ��������ܷ�
	private String comment;// ѧ��Ըÿε����ۼ�����
	private Timestamp completeTime;// �������ʱ��
	private String reply;// ��ʦ�Ļظ�
	private Timestamp replyTime;// �ظ�ʱ��

	public StudentCourseInfo() {

	}

	public StudentCourseInfo(Student student, Course course) {
		super();
		this.student = student;
		this.course = course;
		this.score = -1;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Timestamp completeTime) {
		this.completeTime = completeTime;
	}

	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Timestamp replyTime) {
		this.replyTime = replyTime;
	}

}
