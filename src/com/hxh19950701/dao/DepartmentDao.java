package com.hxh19950701.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hxh19950701.dao.DepartmentDao;
import com.hxh19950701.pojos.Clazz;
import com.hxh19950701.pojos.Department;
import com.hxh19950701.pojos.Subject;

public class DepartmentDao extends HibernateDaoSupport {

	public void saveOrUpdate(Department department){
		department.notifyCreated();
		getHibernateTemplate().saveOrUpdate(department);
	}
	
	public void saveOrUpdate(Subject subject){
		subject.notifyCreated();
		getHibernateTemplate().saveOrUpdate(subject);
	}
	
	public void saveOrUpdate(Clazz clazz){
		clazz.notifyCreated();
		getHibernateTemplate().saveOrUpdate(clazz);
	}
	
	public List<Department> getAllDepartments() {
		String hql = "from Department";
		@SuppressWarnings("unchecked")
		List<Department> list = getHibernateTemplate().find(hql);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public Department getDepartment(int id) {
		String hql = "from Department where id=?";
		@SuppressWarnings("unchecked")
		List<Department> list = getHibernateTemplate().find(hql, id);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public Department getDepartment(String name) {
		String hql = "from Department where name=?";
		@SuppressWarnings("unchecked")
		List<Department> list = getHibernateTemplate().find(hql, name);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public Subject getSubject(int id) {
		String hql = "from Subject where id=?";
		@SuppressWarnings("unchecked")
		List<Subject> list = getHibernateTemplate().find(hql, id);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public Subject getSubject(String name) {
		String hql = "from Subject where name=?";
		@SuppressWarnings("unchecked")
		List<Subject> list = getHibernateTemplate().find(hql, name);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public List<Subject> getAllSubjects(int departmentId) {
		String hql = "from Subject where department.id=?";
		@SuppressWarnings("unchecked")
		List<Subject> list = getHibernateTemplate().find(hql, departmentId);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public Clazz getClazz(int id) {
		String hql = "from Clazz where id=?";
		@SuppressWarnings("unchecked")
		List<Clazz> list = getHibernateTemplate().find(hql, id);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public Clazz getClazz(String name) {
		String hql = "from Clazz where name=?";
		@SuppressWarnings("unchecked")
		List<Clazz> list = getHibernateTemplate().find(hql, name);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public List<Clazz> getAllClazzes(int subjectId) {
		String hql = "from Clazz where subject.id=?";
		@SuppressWarnings("unchecked")
		List<Clazz> list = getHibernateTemplate().find(hql, subjectId);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public List<Clazz> getAllClazzes(int subjectId, int year) {
		String hql = "from Clazz where subject.id=? and year=?";
		@SuppressWarnings("unchecked")
		List<Clazz> list = getHibernateTemplate().find(hql, new int[] { subjectId, year });
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public List<Subject> getAllSubjects() {
		String hql = "from Subject";
		@SuppressWarnings("unchecked")
		List<Subject> list = getHibernateTemplate().find(hql);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public List<Clazz> getAllClazzes() {
		String hql = "from Clazz";
		@SuppressWarnings("unchecked")
		List<Clazz> list = getHibernateTemplate().find(hql);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

}
