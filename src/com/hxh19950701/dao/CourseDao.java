package com.hxh19950701.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hxh19950701.dao.CourseDao;
import com.hxh19950701.pojos.Course;

public class CourseDao extends HibernateDaoSupport{

	public void save(Course course) {
		course.notifyCreated();
		getHibernateTemplate().save(course);
	}

	public void update(Course course) {
		course.notifyUpdated();
		getHibernateTemplate().update(course);
	}

	public Course getCourse(int id) {
		String hql = "from Course where id=?";
		@SuppressWarnings("unchecked")
		List<Course> list = getHibernateTemplate().find(hql, id);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public List<Course> getCourses(int uid) {
		String hql = "from Course where teacher.id=? order by createTime desc";
		@SuppressWarnings("unchecked")
		List<Course> list = getHibernateTemplate().find(hql, uid);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public List<Course> getCourses(String name) {
		String hql = "from Course where name=?";
		@SuppressWarnings("unchecked")
		List<Course> list = getHibernateTemplate().find(hql, name);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	
}
