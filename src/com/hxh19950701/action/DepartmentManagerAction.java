package com.hxh19950701.action;

import java.util.List;

import com.hxh19950701.comm.Constant;
import com.hxh19950701.comm.CustomException;
import com.hxh19950701.comm.CustomException.BaseCustomException;
import com.hxh19950701.pojos.Clazz;
import com.hxh19950701.pojos.Department;
import com.hxh19950701.pojos.Subject;
import com.hxh19950701.service.DepartmentService;

public class DepartmentManagerAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int id = -1;
	private String name;
	private int year;

	private DepartmentService departmentService;

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public Object getDepartmentList() {
		List<Department> departmentList = departmentService.getDepartmentList();
		return departmentList;
	}

	public Object getSubjectList() {
		List<Subject> subjectList = departmentService.getSubjectList();
		return subjectList;
	}

	public Object getSubjectListByDepartment() throws Exception {
		List<Subject> subjectList = departmentService.getSubjectListByDepartment(id);
		return subjectList;
	}

	public Object getClazzList() {
		List<Clazz> clazzList = departmentService.getClazzList();
		return clazzList;
	}

	public Object getClazzListBySubject() throws Exception {
		List<Clazz> clazzList = departmentService.getClazzListBySubject(id);
		return clazzList;
	}

	public Department createDepartment() throws BaseCustomException {
		if (getOnlineUser().getIdentity() == Constant.IDENTITY_ADMINISTRATOR) {
			return departmentService.createDepartment(name);
		}else{
			throw new CustomException.PermissionDeniedException();
		}
	}
	
	public Subject createSubject() throws BaseCustomException {
		if (getOnlineUser().getIdentity() == Constant.IDENTITY_ADMINISTRATOR) {
			return departmentService.createSubject(id, name);
		}else{
			throw new CustomException.PermissionDeniedException();
		}
	}
	
	public Clazz createClazz() throws BaseCustomException {
		if (getOnlineUser().getIdentity() == Constant.IDENTITY_ADMINISTRATOR) {
			return departmentService.createClazz(id, name, year);
		}else{
			throw new CustomException.PermissionDeniedException();
		}
	}
}
