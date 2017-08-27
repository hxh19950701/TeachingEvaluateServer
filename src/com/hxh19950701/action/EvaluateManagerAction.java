package com.hxh19950701.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hxh19950701.comm.Constant;
import com.hxh19950701.comm.CustomException;
import com.hxh19950701.comm.CustomException.BaseCustomException;
import com.hxh19950701.pojos.Course;
import com.hxh19950701.pojos.EvaluateThirdTarget;
import com.hxh19950701.pojos.StudentCourseEvaluate;
import com.hxh19950701.pojos.StudentCourseInfo;
import com.hxh19950701.pojos.TeacherCourseEvaluate;
import com.hxh19950701.pojos.User;
import com.hxh19950701.service.CourseService;
import com.hxh19950701.service.EvaluateService;

public class EvaluateManagerAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private EvaluateService evaluateService;
	private CourseService courseService;

	private int id = -1;
	private int uid = -1;
	private int courseId = -1;
	private int itemId = -1;
	private float score = -1;
	private String comment = "";

	public void setId(int id) {
		this.id = id;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setEvaluateService(EvaluateService evaluateService) {
		this.evaluateService = evaluateService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public Object getAllTargets() {
		List<EvaluateThirdTarget> targetList = evaluateService.getAllEvaluateThirdTargets();
		return targetList;
	}

	public Map<String, Object> getStudentAllEvaluatedItemsByCourse() throws Exception {
		User user = getOnlineUser();
		Map<String, Object> result = new HashMap<String, Object>();
		Course course = courseService.getCourse(courseId);
		List<StudentCourseEvaluate> item = null;
		switch (user.getIdentity()) {
			case Constant.IDENTITY_STUDENT:
				item = evaluateService.getStudentCourseEvaluates(user.getId(), courseId);
				break;
			case Constant.IDENTITY_TEACHER:
				throw new CustomException.PermissionDeniedException();
			case Constant.IDENTITY_ADMINISTRATOR:
				item = evaluateService.getStudentCourseEvaluates(uid, courseId);
				break;
		}
		result.put("course", course);
		result.put("item", item);
		return result;
	}

	public List<TeacherCourseEvaluate> getCourseItemScore() throws Exception {
		User user = getOnlineUser();
		switch (user.getIdentity()) {
			case Constant.IDENTITY_STUDENT:
				throw new CustomException.PermissionDeniedException();
			case Constant.IDENTITY_TEACHER:
				Course course = courseService.getCourse(courseId);
				if (course.getTeacher().getId() == user.getId()) {
					return evaluateService.getTeacherCourseEvaluates(courseId);
				}
				else {
					throw new CustomException.PermissionDeniedException();
				}
			case Constant.IDENTITY_ADMINISTRATOR:
				return evaluateService.getTeacherCourseEvaluates(courseId);
		}
		return null;
	}

	public Object updateItemScore() throws Exception {
		User user = getOnlineUser();
		StudentCourseEvaluate studentCourseEvaluate = null;
		switch (user.getIdentity()) {
			case Constant.IDENTITY_STUDENT:
				studentCourseEvaluate = evaluateService.updateItemScore(user.getId(), courseId, itemId, score);
				break;
			case Constant.IDENTITY_TEACHER:
				throw new CustomException.PermissionDeniedException();
			case Constant.IDENTITY_ADMINISTRATOR:
				studentCourseEvaluate = evaluateService.updateItemScore(uid, courseId, itemId, score);
				break;
		}
		return studentCourseEvaluate;
	}

	public Object commitEvaluate() throws Exception {
		User user = getOnlineUser();
		StudentCourseInfo studentCourseInfo = null;
		switch (user.getIdentity()) {
			case Constant.IDENTITY_STUDENT:
				studentCourseInfo = evaluateService.commitEvaluate(user.getId(), courseId);
				break;
			case Constant.IDENTITY_TEACHER:
				throw new CustomException.PermissionDeniedException();
			case Constant.IDENTITY_ADMINISTRATOR:
				studentCourseInfo = evaluateService.commitEvaluate(uid, courseId);
				break;
		}
		return studentCourseInfo;
	}

	public StudentCourseInfo commentCourse() throws Exception {
		User user = getOnlineUser();
		StudentCourseInfo studentCourseInfo = null;
		switch (user.getIdentity()) {
			case Constant.IDENTITY_STUDENT:
				studentCourseInfo = evaluateService.commentCourse(user.getId(), courseId, comment);
				break;
			case Constant.IDENTITY_TEACHER:
				throw new CustomException.PermissionDeniedException();
			case Constant.IDENTITY_ADMINISTRATOR:
				studentCourseInfo = evaluateService.commentCourse(uid, courseId, comment);
				break;
		}
		return studentCourseInfo;
	}
	
	public StudentCourseInfo reply() throws Exception{
		User user = getOnlineUser();
		StudentCourseInfo studentCourseInfo = null;
		switch (user.getIdentity()) {
			case Constant.IDENTITY_STUDENT:
				throw new CustomException.PermissionDeniedException();
			case Constant.IDENTITY_TEACHER:
				evaluateService.reply(id, comment);
				break;
			case Constant.IDENTITY_ADMINISTRATOR:
				studentCourseInfo = evaluateService.commentCourse(uid, courseId, comment);
				break;
		}
		return studentCourseInfo;
	}

	public List<TeacherCourseEvaluate> getTeacherAllEvaluatedItemsByCourse() throws Exception {
		User user = getOnlineUser();
		List<TeacherCourseEvaluate> teacherCourseEvaluate = null;
		switch (user.getIdentity()) {
			case Constant.IDENTITY_STUDENT:
				throw new CustomException.PermissionDeniedException();
			case Constant.IDENTITY_TEACHER:
				Course course = courseService.getCourse(courseId);
				if (course.getTeacher().getId() == user.getId()) {
					teacherCourseEvaluate = evaluateService.getTeacherCourseEvaluates(courseId);
				}
				else {
					throw new CustomException.PermissionDeniedException();
				}
				break;
			case Constant.IDENTITY_ADMINISTRATOR:
				teacherCourseEvaluate = evaluateService.getTeacherCourseEvaluates(courseId);
				break;
		}
		return teacherCourseEvaluate;
	}

	public List<StudentCourseInfo> getCompletedEvaluation() throws BaseCustomException {
		User user = getOnlineUser();
		List<StudentCourseInfo> studentCourseInfo = null;
		switch (user.getIdentity()) {
			case Constant.IDENTITY_STUDENT:
				throw new CustomException.PermissionDeniedException();
			case Constant.IDENTITY_TEACHER:
				Course course = courseService.getCourse(courseId);
				if (course.getTeacher().getId() == user.getId()) {
					studentCourseInfo = evaluateService.getCompletedEvaluate(courseId);
				}
				else {
					throw new CustomException.PermissionDeniedException();
				}
				break;
			case Constant.IDENTITY_ADMINISTRATOR:
				studentCourseInfo = evaluateService.getCompletedEvaluate(courseId);
				break;
		}
		return studentCourseInfo;
	}

	public Map<String, TeacherCourseEvaluate> getMaxAndMin() throws BaseCustomException {
		User user = getOnlineUser();
		switch (user.getIdentity()) {
			case Constant.IDENTITY_STUDENT:
				throw new CustomException.PermissionDeniedException();
			case Constant.IDENTITY_TEACHER:
				Course course = courseService.getCourse(courseId);
				if (course.getTeacher().getId() == user.getId()) {
					return evaluateService.getMaxAndMin(courseId);
				}
				else {
					throw new CustomException.PermissionDeniedException();
				}
			case Constant.IDENTITY_ADMINISTRATOR:
				return evaluateService.getMaxAndMin(courseId);
			default:
				return null;
		}
	}
}
