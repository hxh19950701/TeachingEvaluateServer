package com.hxh19950701.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hxh19950701.dao.StudentCourseInfoDao;
import com.hxh19950701.pojos.StudentCourseInfo;

public class StudentCourseInfoDao extends HibernateDaoSupport {

	public void save(StudentCourseInfo studentCourseInfo) {
		getHibernateTemplate().save(studentCourseInfo);
	}

	public void update(StudentCourseInfo studentCourseInfo) {
		getHibernateTemplate().update(studentCourseInfo);
	}

	public StudentCourseInfo getStudentCourseInfo(int id) {
		String hql = "from StudentCourseInfo where id=?";
		@SuppressWarnings("unchecked")
		List<StudentCourseInfo> list = (List<StudentCourseInfo>) getHibernateTemplate().find(hql,
				id);
		if (list == null || list.size() == 0) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	public StudentCourseInfo getStudentCourseInfo(int studentId, int courseId) {
		String hql = "from StudentCourseInfo where student.id=? and course.id=?";
		@SuppressWarnings("unchecked")
		List<StudentCourseInfo> list = (List<StudentCourseInfo>) getHibernateTemplate().find(hql,
				new Object[] { studentId, courseId });
		if (list == null || list.size() == 0) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	public List<StudentCourseInfo> getStudentCourseInfos(int id) {
		String hql = "from StudentCourseInfo where student.id=? order by completeTime desc";
		@SuppressWarnings("unchecked")
		List<StudentCourseInfo> list = (List<StudentCourseInfo>) getHibernateTemplate().find(hql, id);
		if (list == null || list.size() == 0) {
			return null;
		}
		else {
			return list;
		}
	}

	public List<StudentCourseInfo> getCompletedEvaluate(int courseId) {
		String hql = "from StudentCourseInfo where course.id=? and score >= 0.0 order by completeTime desc";
		@SuppressWarnings("unchecked")
		List<StudentCourseInfo> list = (List<StudentCourseInfo>) getHibernateTemplate().find(hql,
				courseId);
		if (list == null || list.size() == 0) {
			return null;
		}
		else {
			return list;
		}
	}
}
