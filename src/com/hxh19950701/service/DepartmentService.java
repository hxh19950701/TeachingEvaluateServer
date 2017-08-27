package com.hxh19950701.service;

import java.util.List;

import com.hxh19950701.comm.CheckUtils;
import com.hxh19950701.comm.CustomException;
import com.hxh19950701.comm.CustomException.BaseCustomException;
import com.hxh19950701.comm.TextUtils;
import com.hxh19950701.dao.DepartmentDao;
import com.hxh19950701.pojos.Clazz;
import com.hxh19950701.pojos.Department;
import com.hxh19950701.pojos.Subject;

public class DepartmentService {

	private DepartmentDao departmentDao;

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public List<Department> getDepartmentList() {
		return departmentDao.getAllDepartments();
	}

	public List<Subject> getSubjectList() {
		List<Subject> subjectList = departmentDao.getAllSubjects();
		return subjectList;
	}

	public List<Subject> getSubjectListByDepartment(int departmentId) throws Exception {
		CheckUtils.checkDepartmentId(departmentId);

		List<Subject> subjectList = departmentDao.getAllSubjects(departmentId);
		return subjectList;
	}

	public List<Clazz> getClazzList() {
		List<Clazz> clazzList = departmentDao.getAllClazzes();
		return clazzList;
	}

	public List<Clazz> getClazzListBySubject(int subjectId) throws Exception {
		CheckUtils.checkSubjectId(subjectId);

		List<Clazz> clazzList = departmentDao.getAllClazzes(subjectId);
		return clazzList;
	}

	public Department createDepartment(String name) throws BaseCustomException {
		if (TextUtils.isEmpty(name)) {
			throw new CustomException.EmptyNameException();
		}
		Department department = new Department(name);
		departmentDao.saveOrUpdate(department);
		return department;
	}

	public Subject createSubject(int departmentId, String name) throws BaseCustomException {
		if (TextUtils.isEmpty(name)) {
			throw new CustomException.EmptyNameException();
		}
		Department department = departmentDao.getDepartment(departmentId);
		if (department == null) {
			throw new CustomException.NoSuchDepartmentException(departmentId);
		}
		Subject subject = new Subject(department, name);
		departmentDao.saveOrUpdate(subject);
		return subject;
	}

	public Clazz createClazz(int subjectId, String name, int year) throws BaseCustomException {
		CheckUtils.checkSubjectId(subjectId);
		CheckUtils.checkYear(year);
		if (TextUtils.isEmpty(name)) {
			throw new CustomException.EmptyNameException();
		}
		Subject subject = departmentDao.getSubject(subjectId);
		if (subject == null) {
			throw new CustomException.NoSuchSubjectException(subjectId);
		}
		Clazz clazz = new Clazz(name, year, subject, null);
		departmentDao.saveOrUpdate(clazz);
		return clazz;
	}
}
