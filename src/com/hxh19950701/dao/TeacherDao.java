package com.hxh19950701.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hxh19950701.dao.TeacherDao;
import com.hxh19950701.pojos.Teacher;

public class TeacherDao extends HibernateDaoSupport {

	public void save(Teacher teacher) {
		teacher.notifyCreated();
		getHibernateTemplate().save(teacher);
	}

	public void update(Teacher teacher) {
		teacher.notifyUpdated();
		getHibernateTemplate().update(teacher);
	}

	public Teacher getTeacher(int id) {
		String hql = "from Teacher where id=?";
		@SuppressWarnings("unchecked")
		ArrayList<Teacher> list = (ArrayList<Teacher>) getHibernateTemplate().find(hql, new Integer[]{id});
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public Teacher getTeacher(String teacherId) {
		String hql = "from Teacher where teacherId=?";
		@SuppressWarnings("unchecked")
		List<Teacher> list = getHibernateTemplate().find(hql, teacherId);
		if (list == null || list.size() == 0) {
			return null;
		}
		else {
			return list.get(0);
		}
	}
	
	public List<Teacher> getAllTeachers() {
		String hql = "from Teacher";
		@SuppressWarnings("unchecked")
		List<Teacher> list = getHibernateTemplate().find(hql);
		if (list == null || list.size() == 0) {
			return null;
		}
		else {
			return list;
		}
	}

}
