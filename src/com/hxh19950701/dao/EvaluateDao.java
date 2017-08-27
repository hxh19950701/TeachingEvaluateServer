package com.hxh19950701.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hxh19950701.dao.EvaluateDao;
import com.hxh19950701.pojos.StudentCourseEvaluate;
import com.hxh19950701.pojos.TeacherCourseEvaluate;

public class EvaluateDao extends HibernateDaoSupport {

	public void save(StudentCourseEvaluate studentCourseEvaluate) {
		studentCourseEvaluate.notifyCreated();
		getHibernateTemplate().saveOrUpdate(studentCourseEvaluate);
	}

	public void update(StudentCourseEvaluate studentCourseEvaluate) {
		studentCourseEvaluate.notifyUpdated();
		getHibernateTemplate().saveOrUpdate(studentCourseEvaluate);
	}

	public void save(TeacherCourseEvaluate teacherCourseEvaluate) {
		teacherCourseEvaluate.notifyCreated();
		getHibernateTemplate().saveOrUpdate(teacherCourseEvaluate);
	}

	public void update(TeacherCourseEvaluate teacherCourseEvaluate) {
		teacherCourseEvaluate.notifyUpdated();
		getHibernateTemplate().saveOrUpdate(teacherCourseEvaluate);
	}

	public StudentCourseEvaluate getStudentCourseEvaluate(int id) {
		return (StudentCourseEvaluate) getHibernateTemplate().get(StudentCourseEvaluate.class, id);
	}

	public StudentCourseEvaluate getStudentCourseEvaluate(int studentId, int courseId,
			int thirdTargetId) {
		String hql = "from StudentCourseEvaluate where info.student.id=? and info.course.id=? and item.id=?";
		@SuppressWarnings("unchecked")
		List<StudentCourseEvaluate> list = getHibernateTemplate().find(hql,
				new Object[] { studentId, courseId, thirdTargetId });
		if (list == null || list.size() == 0) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	public List<StudentCourseEvaluate> getStudentCourseEvaluatesByStudent(int studentId) {
		String hql = "from StudentCourseEvaluate where info.student.id=? order by item";
		@SuppressWarnings("unchecked")
		List<StudentCourseEvaluate> list = getHibernateTemplate()
				.find(hql, new int[] { studentId });
		if (list == null || list.size() == 0) {
			return null;
		}
		else {
			return list;
		}
	}

	public List<StudentCourseEvaluate> getStudentCourseEvaluatesByCourse(int courseId) {
		String hql = "from StudentCourseEvaluate where info.course.id=?";
		@SuppressWarnings("unchecked")
		List<StudentCourseEvaluate> list = getHibernateTemplate()
				.find(hql, new int[] { courseId });
		if (list == null || list.size() == 0) {
			return null;
		}
		else {
			return list;
		}
	}

	public List<StudentCourseEvaluate> getStudentCourseEvaluates(int studentId, int courseId) {
		String hql = "from StudentCourseEvaluate where info.student.id=? and info.course.id=?";
		@SuppressWarnings("unchecked")
		List<StudentCourseEvaluate> list = getHibernateTemplate().find(hql, new Object[] { studentId, courseId });
		if (list == null || list.size() == 0) {
			return null;
		}
		else {
			return list;
		}
	}

	public List<TeacherCourseEvaluate> getTeacherCourseEvaluatesByCourse(int courseId) {
		String hql = "from TeacherCourseEvaluate where course.id=? order by item";
		@SuppressWarnings("unchecked")
		List<TeacherCourseEvaluate> list = getHibernateTemplate().find(hql, new Object[] { courseId });
		if (list == null || list.size() == 0) {
			return null;
		}
		else {
			return list;
		}
	}

}
