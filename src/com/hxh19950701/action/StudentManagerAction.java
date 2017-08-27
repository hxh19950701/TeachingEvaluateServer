package com.hxh19950701.action;

import com.hxh19950701.comm.Constant;
import com.hxh19950701.comm.CustomException;
import com.hxh19950701.comm.CustomException.BaseCustomException;
import com.hxh19950701.pojos.Student;
import com.hxh19950701.pojos.User;
import com.hxh19950701.service.StudentService;

public class StudentManagerAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int uid = -1;
	private String studentId = null;
	private String name = null;
	private int sex = -1;
	private int clazzId = -1;

	private StudentService studentService;

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public void setClazzId(int clazzId) {
		this.clazzId = clazzId;
	}

	public boolean hasExist() throws Exception {
		return studentService.hasExist(studentId);
	}

	public Student register() throws Exception {
		User user = getOnlineUser();
		switch (user.getIdentity()) {
			case Constant.IDENTITY_STUDENT:
				return studentService.register(user.getId(), studentId, name, sex, clazzId);
			case Constant.IDENTITY_TEACHER:
				throw new CustomException.PermissionDeniedException();
			case Constant.IDENTITY_ADMINISTRATOR:
				return studentService.register(uid, studentId, name, sex, clazzId);
		}
		return null;
	}

	public Student currentStudent() throws Exception {
		User user = getOnlineUser();
		Student student = studentService.getStudentByUid(user.getId());
		return student;
	}
	
	public Student get() throws BaseCustomException{
		if (getOnlineUser().getIdentity() == Constant.IDENTITY_ADMINISTRATOR) {
			return studentService.getStudentByUid(uid);
		}
		else {
			throw new CustomException.PermissionDeniedException();
		}
	}
}
