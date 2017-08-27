package com.hxh19950701.action;

import java.util.List;

import com.hxh19950701.comm.Constant;
import com.hxh19950701.comm.CustomException;
import com.hxh19950701.pojos.Course;
import com.hxh19950701.pojos.StudentCourseInfo;
import com.hxh19950701.pojos.User;
import com.hxh19950701.service.CourseService;

public class CourseManagerAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private int courseId = -1;
	private String courseName = null;
	private int term = -1;
	private int year = -1;
	private String password = null;
	private int uid = -1;
	private int totalPersonCount = -1;

	private CourseService courseService;

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public void setTotalPersonCount(int totalPersonCount) {
		this.totalPersonCount = totalPersonCount;
	}

	public Course getCourse() throws Exception {
		return courseService.getCourse(courseId);
	}

	public List<StudentCourseInfo> getStudentCourseList() throws Exception {
		User user = getOnlineUser();
		List<StudentCourseInfo> courseList = null;
		switch (user.getIdentity()) {
			case Constant.IDENTITY_STUDENT:
				courseList = courseService.getStudentCourseInfos(user.getId());
				break;
			case Constant.IDENTITY_TEACHER:
				throw new CustomException.PermissionDeniedException();
			case Constant.IDENTITY_ADMINISTRATOR:
				courseList = courseService.getStudentCourseInfos(uid);
				break;
		}
		return courseList;
	}

	public List<Course> getTeacherCourseList() throws Exception {
		User user = getOnlineUser();
		List<Course> courseList = null;
		switch (user.getIdentity()) {
			case Constant.IDENTITY_STUDENT:
				throw new CustomException.PermissionDeniedException();
			case Constant.IDENTITY_TEACHER:
				courseList = courseService.getTeacherCourseInfos(user.getId());
				break;
			case Constant.IDENTITY_ADMINISTRATOR:
				courseList = courseService.getTeacherCourseInfos(uid);
				break;
		}
		return courseList;
	}

	public StudentCourseInfo addCourse() throws Exception {
		User user = getOnlineUser();
		StudentCourseInfo course = null;
		switch (user.getIdentity()) {
			case Constant.IDENTITY_STUDENT:
				course = courseService.addCourse(user.getId(), courseId, password);
				break;
			case Constant.IDENTITY_TEACHER:
				throw new CustomException.PermissionDeniedException();
			case Constant.IDENTITY_ADMINISTRATOR:
				course = courseService.addCourse(uid, courseId, password);
				break;
		}
		return course;
	}

	public Course newCourse() throws Exception {
		User user = getOnlineUser();
		Course course = null;
		switch (user.getIdentity()) {
			case Constant.IDENTITY_STUDENT:
				throw new CustomException.PermissionDeniedException();
			case Constant.IDENTITY_TEACHER:
				course = courseService.newCourse(user.getId(), courseName, year, term, totalPersonCount, password);
				break;
			case Constant.IDENTITY_ADMINISTRATOR:
				course = courseService.newCourse(uid, courseName, year, term, totalPersonCount, password);
				break;
		}
		return course;
	}
}
