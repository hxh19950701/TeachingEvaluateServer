package com.hxh19950701.service;

import java.util.List;

import com.hxh19950701.comm.CheckUtils;
import com.hxh19950701.comm.CustomException;
import com.hxh19950701.dao.CourseDao;
import com.hxh19950701.dao.StudentCourseInfoDao;
import com.hxh19950701.dao.StudentDao;
import com.hxh19950701.dao.TeacherDao;
import com.hxh19950701.pojos.Course;
import com.hxh19950701.pojos.Student;
import com.hxh19950701.pojos.StudentCourseInfo;
import com.hxh19950701.pojos.Teacher;
import com.hxh19950701.service.CourseService;

public class CourseService {

	private StudentCourseInfoDao studentCourseInfoDao;
	private CourseDao courseDao;
	private StudentDao studentDao;
	private TeacherDao teacherDao;

	public void setStudentCourseInfoDao(StudentCourseInfoDao studentCourseInfoDao) {
		this.studentCourseInfoDao = studentCourseInfoDao;
	}

	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public Course getCourse(int courseId) throws CustomException.BaseCustomException {
		CheckUtils.checkCourseId(courseId);
		return courseDao.getCourse(courseId);
	}

	public List<StudentCourseInfo> getStudentCourseInfos(int uid) throws CustomException.BaseCustomException {
		CheckUtils.checkUid(uid);
		return studentCourseInfoDao.getStudentCourseInfos(uid);
	}

	public List<Course> getTeacherCourseInfos(int uid) throws CustomException.BaseCustomException {
		CheckUtils.checkUid(uid);
		return courseDao.getCourses(uid);
	}

	private StudentCourseInfo addCourse(Student student, Course course)
			throws CustomException.BaseCustomException {
		// 获取该学生所有已添加的课程列表
		List<StudentCourseInfo> list
			= studentCourseInfoDao.getStudentCourseInfos(student.getId());
		// 如果列表不为空，检查是否已添加此课程
		if (list != null) {
			for (int i = 0; i < list.size(); ++i) {
				if (list.get(i).getCourse().getId() == course.getId()) {
					throw new CustomException.AddCourseRepeatedlyException(course.getId());
				}
			}
		}
		if (course.getPersonCount() + 1 <= course.getTotalPersonCount()) {
			// 添加课程
			StudentCourseInfo studentCourseInfo = new StudentCourseInfo(student, course);
			studentCourseInfoDao.save(studentCourseInfo);
			// 更新课程信息
			course.setPersonCount(course.getPersonCount() + 1);
			courseDao.update(course);
			return studentCourseInfo;
		}
		else {
			throw new CustomException.PersonCountLimitExceedException();
		}
	}

	public StudentCourseInfo addCourse(int uid, int courseId,
			String password) throws CustomException.BaseCustomException {
		CheckUtils.checkUid(uid);
		CheckUtils.checkCourseId(courseId);
		CheckUtils.checkPassword(password);

		Course course = courseDao.getCourse(courseId);
		Student student = studentDao.getStudentById(uid);
		if (course == null) {
			throw new CustomException.NoSuchCourseException(courseId);
		}
		if (student == null) {
			throw new CustomException.NoSuchStudentException(courseId);
		}
		if (course.getPassword().equals(password)) {
			return addCourse(student, course);
		}
		else {
			throw new CustomException.WrongPasswordException();
		}
	}

	public StudentCourseInfo addCourse(int uid, int courseId) throws CustomException.BaseCustomException {
		CheckUtils.checkUid(uid);
		CheckUtils.checkCourseId(courseId);

		Course course = courseDao.getCourse(courseId);
		Student student = studentDao.getStudentById(uid);
		if (course == null) {
			throw new CustomException.NoSuchCourseException(courseId);
		}
		if (student == null) {
			throw new CustomException.NoSuchStudentException(courseId);
		}
		return addCourse(student, course);
	}

	public Course newCourse(int uid, String courseName, int year, int term,
			int totalPersonCount, String password)
					throws CustomException.BaseCustomException {
		CheckUtils.checkUid(uid);
		CheckUtils.checkCourseName(courseName);
		CheckUtils.checkYear(year);
		CheckUtils.checkTerm(term);
		CheckUtils.checkPersonCount(totalPersonCount);
		CheckUtils.checkPassword(password);

		Teacher teacher = teacherDao.getTeacher(uid);
		if (teacher == null) {
			throw new CustomException.NoSuchTeacherException(uid);
		}
		else {
			Course course = new Course(courseName, teacher, year, term,
					totalPersonCount, password);
			courseDao.save(course);
			return course;
		}
	}

}
