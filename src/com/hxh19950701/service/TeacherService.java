package com.hxh19950701.service;

import com.hxh19950701.comm.CheckUtils;
import com.hxh19950701.comm.CustomException;
import com.hxh19950701.dao.TeacherDao;
import com.hxh19950701.pojos.Teacher;

public class TeacherService {
	private TeacherDao teacherDao;

	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	public boolean hasExist(String teacherId) throws CustomException.BaseCustomException {
		CheckUtils.checkTeacherId(teacherId);
		return teacherDao.getTeacher(teacherId) != null;
	}

	public Teacher register(int uid, String teacherId,
			String name, int sex) throws CustomException.BaseCustomException {
		CheckUtils.checkUid(uid);
		CheckUtils.checkTeacherId(teacherId);
		CheckUtils.checkPersonName(name);
		CheckUtils.checkSex(sex);

		Teacher teacher = new Teacher(uid, teacherId, name, sex);
		teacherDao.save(teacher);
		return teacher;
	}

	public Teacher getTeacherByUid(int uid) throws CustomException.BaseCustomException {
		CheckUtils.checkUid(uid);
		return teacherDao.getTeacher(uid);
	}
}
