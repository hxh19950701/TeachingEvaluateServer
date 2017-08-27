package com.hxh19950701.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hxh19950701.dao.StudentDao;
import com.hxh19950701.pojos.Student;

public class StudentDao extends HibernateDaoSupport {

	public void save(Student student) {
		student.notifyCreated();
		getHibernateTemplate().save(student);
	}

	public void update(Student student) {
		student.notifyUpdated();
		getHibernateTemplate().update(student);
	}

	public Student getStudentById(int id) {
		String hql = "from Student where id=?";
		@SuppressWarnings("unchecked")
		ArrayList<Student> list = (ArrayList<Student>) getHibernateTemplate().find(hql, id);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public Student getStudentByStudentId(String studentId) {
		String hql = "from Student where studentId=?";
		@SuppressWarnings("unchecked")
		List<Student> list = (List<Student>) getHibernateTemplate().find(hql, studentId);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public List<Student> getStudentsByName(String name) {
		String hql = "from Student where name=?";
		@SuppressWarnings("unchecked")
		List<Student> list = (List<Student>) getHibernateTemplate().find(hql, name);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public List<Student> getStudentsBySex(int sex) {
		String hql = "from Student where sex=?";
		@SuppressWarnings("unchecked")
		List<Student> list = (List<Student>) getHibernateTemplate().find(hql, sex);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public List<Student> getStudentsBySubject(int subjectId) {
		String hql = "from Student where subject.id=?";
		@SuppressWarnings("unchecked")
		List<Student> list = (List<Student>) getHibernateTemplate().find(hql, subjectId);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public List<Student> getStudentsByDepartment(int departmentId) {
		String hql = "from Student where department.id=?";
		@SuppressWarnings("unchecked")
		List<Student> list = (List<Student>) getHibernateTemplate().find(hql, departmentId);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

}
