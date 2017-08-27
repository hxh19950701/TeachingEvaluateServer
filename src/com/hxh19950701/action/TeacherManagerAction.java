package com.hxh19950701.action;

import com.hxh19950701.comm.CheckUtils;
import com.hxh19950701.comm.Constant;
import com.hxh19950701.comm.CustomException;
import com.hxh19950701.pojos.Code;
import com.hxh19950701.pojos.Teacher;
import com.hxh19950701.pojos.User;
import com.hxh19950701.service.CodeService;
import com.hxh19950701.service.TeacherService;

public class TeacherManagerAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int uid = -1;
	private String teacherId = null;
	private int sex = -1;
	private String name = null;

	private CodeService codeService;
	private TeacherService teacherService;

	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}
	
	public Object register() throws Exception {
		User user = getOnlineUser();
		switch (user.getIdentity()) {
			case Constant.IDENTITY_STUDENT:
				throw new CustomException.PermissionDeniedException();
			case Constant.IDENTITY_TEACHER:
				return teacherService.register(user.getId(), teacherId, name, sex);
			case Constant.IDENTITY_ADMINISTRATOR:
				return teacherService.register(uid, teacherId, name, sex);
		}
		return null;
	}

	public Object currentTeacher() throws Exception {
		User user = getOnlineUser();
		Teacher teacher = teacherService.getTeacherByUid(user.getId());
		return teacher;
	}

	public Teacher getTeacherByUid() throws CustomException.BaseCustomException {
		CheckUtils.checkUid(uid);
		return teacherService.getTeacherByUid(uid);
	}
	
	public Code createCode() throws CustomException.BaseCustomException {
		User user = getOnlineUser();
		switch (user.getIdentity()) {
			case Constant.IDENTITY_STUDENT:
				throw new CustomException.PermissionDeniedException();
			case Constant.IDENTITY_TEACHER:
				return codeService.create();
			case Constant.IDENTITY_ADMINISTRATOR:
				throw new CustomException.PermissionDeniedException();
		}
		return null;
	}
}