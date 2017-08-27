package com.hxh19950701.service;

import com.hxh19950701.comm.CheckUtils;
import com.hxh19950701.comm.CustomException;
import com.hxh19950701.dao.DepartmentDao;
import com.hxh19950701.dao.StudentDao;
import com.hxh19950701.pojos.Clazz;
import com.hxh19950701.pojos.Student;
import com.hxh19950701.service.StudentService;

public class StudentService {

	private StudentDao studentDao;
	private DepartmentDao departmentDao;

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public boolean hasExist(String studentId) throws CustomException.BaseCustomException {
		CheckUtils.checkStudentId(studentId);

		return studentDao.getStudentByStudentId(studentId) != null;
	}

	public Student register(int uid, String studentId, String name,
			int sex, int clazzId) throws CustomException.BaseCustomException {
		CheckUtils.checkUid(uid);
		CheckUtils.checkStudentId(studentId);
		CheckUtils.checkPersonName(name);
		CheckUtils.checkSex(sex);
		CheckUtils.checkClazzId(clazzId);

		Clazz clazz = departmentDao.getClazz(clazzId);
		if (clazz == null) {
			throw new CustomException.NoSuchClazzException(clazzId);
		}
		else if (studentDao.getStudentById(uid) != null) {
			throw new CustomException.StudentExistException();
		}
		else {
			if (studentDao.getStudentByStudentId(studentId) == null) {
				Student student = new Student(uid, studentId, name, sex, clazz);
				studentDao.save(student);
				return student;
			}
			else {
				throw new CustomException.StudentIdExistException(studentId);
			}
		}
	}

	public Student getStudentByUid(int uid) throws CustomException.BaseCustomException {
		CheckUtils.checkUid(uid);
		return studentDao.getStudentById(uid);
	}

}
