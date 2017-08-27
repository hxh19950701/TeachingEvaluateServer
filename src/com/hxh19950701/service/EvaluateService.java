package com.hxh19950701.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hxh19950701.comm.CheckUtils;
import com.hxh19950701.comm.CustomException;
import com.hxh19950701.comm.CustomException.BaseCustomException;
import com.hxh19950701.comm.CustomException.InvalidCourseIdException;
import com.hxh19950701.dao.CourseDao;
import com.hxh19950701.dao.EvaluateDao;
import com.hxh19950701.dao.EvaluateTargetDao;
import com.hxh19950701.dao.StudentCourseInfoDao;
import com.hxh19950701.pojos.Course;
import com.hxh19950701.pojos.EvaluateThirdTarget;
import com.hxh19950701.pojos.StudentCourseEvaluate;
import com.hxh19950701.pojos.StudentCourseInfo;
import com.hxh19950701.pojos.TeacherCourseEvaluate;
import com.hxh19950701.service.EvaluateService;

public class EvaluateService {

	private EvaluateTargetDao evaluateTargetDao;
	private EvaluateDao evaluateDao;
	private StudentCourseInfoDao studentCourseInfoDao;
	private CourseDao courseDao;

	public void setEvaluateTargetDao(EvaluateTargetDao evaluateTargetDao) {
		this.evaluateTargetDao = evaluateTargetDao;
	}

	public void setEvaluateDao(EvaluateDao evaluateDao) {
		this.evaluateDao = evaluateDao;
	}

	public void setStudentCourseInfoDao(StudentCourseInfoDao studentCourseInfoDao) {
		this.studentCourseInfoDao = studentCourseInfoDao;
	}

	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	public List<EvaluateThirdTarget> getAllEvaluateThirdTargets() {
		return evaluateTargetDao.getAllEvaluateThirdTargets();
	}

	public List<StudentCourseEvaluate> getStudentCourseEvaluates(int uid, int courseId) throws CustomException.BaseCustomException {
		CheckUtils.checkUid(uid);
		CheckUtils.checkCourseId(courseId);

		return evaluateDao.getStudentCourseEvaluates(uid, courseId);
	}

	public StudentCourseEvaluate updateItemScore(int uid, int courseId,
			int itemId, float score) throws CustomException.BaseCustomException {
		CheckUtils.checkUid(uid);
		CheckUtils.checkCourseId(courseId);
		CheckUtils.checkItemId(itemId);

		// 获取当前评价项
		StudentCourseEvaluate studentCourseEvaluate
			= evaluateDao.getStudentCourseEvaluate(uid, courseId, itemId);
		// 为空，则说明为第一次提交，新建一个
		if (studentCourseEvaluate == null) {
			StudentCourseInfo studentCourseInfo
				= studentCourseInfoDao.getStudentCourseInfo(uid, courseId);
			EvaluateThirdTarget evaluateThirdTarget
				= evaluateTargetDao.getEvaluateThirdTarget(itemId);
			studentCourseEvaluate = new StudentCourseEvaluate(
					studentCourseInfo, evaluateThirdTarget, score);
		}
		// 检查当前评分是否已经提交，提交后将不能再改动
		if (studentCourseEvaluate.getInfo().getScore() >= 0) {
			throw new CustomException.ScoreCommittedException(
					studentCourseEvaluate.getId());
		}
		else {
			// 检查评价分是否合法，不能为负分也不能高于项总分
			if (score >= 0.0 
					&& score <= studentCourseEvaluate.getItem().getTotalScore()) {
				// 更新评价并保存
				studentCourseEvaluate.setScore(score);
				evaluateDao.update(studentCourseEvaluate);
				return studentCourseEvaluate;
			}
			else {
				throw new CustomException.InvalidScoreException(score);
			}
		}
	}

	private float getTotalScore(List<StudentCourseEvaluate> items) throws CustomException.BaseCustomException {
		List<EvaluateThirdTarget> evaluateTarget = evaluateTargetDao.getAllEvaluateThirdTargets();
		float sum = 0.0f;
		CheckUtils.checkEvaluateTarget(evaluateTarget);
		if (evaluateTarget.size() != items.size()) {
			throw new CustomException.EvaluateNotFinishException();
		}
		for (StudentCourseEvaluate item : items) {
			float itemScore = item.getScore();
			if (itemScore < 0) {
				throw new CustomException.EvaluateNotFinishException();
			}
			sum += itemScore;
		}
		return sum;
	}

	private void updateTeacherCourseEvaluateItem(List<StudentCourseEvaluate> items, int evaluatedPeopleCount) throws CustomException.BaseCustomException {
		List<EvaluateThirdTarget> evaluateTarget = evaluateTargetDao.getAllEvaluateThirdTargets();
		Course course = items.get(0).getInfo().getCourse();
		List<TeacherCourseEvaluate> teacherItems = getTeacherCourseEvaluates(course.getId());
		if (teacherItems == null) {
			teacherItems = new ArrayList<TeacherCourseEvaluate>(evaluateTarget.size());
			for (EvaluateThirdTarget thirdTarget : evaluateTarget) {
				TeacherCourseEvaluate teacherItem = new TeacherCourseEvaluate(course, thirdTarget);
				teacherItems.add(teacherItem);
			}
		}
		for (int ix = 0; ix < teacherItems.size(); ++ix) {
			TeacherCourseEvaluate teacherItem = teacherItems.get(ix);
			float totalScore = teacherItem.getScore() * evaluatedPeopleCount;
			float score = (totalScore + items.get(ix).getScore()) / (evaluatedPeopleCount + 1);
			teacherItem.setScore(score);
			evaluateDao.update(teacherItem);
		}
	}

	public StudentCourseInfo commitEvaluate(int uid, int courseId) throws CustomException.BaseCustomException {
		CheckUtils.checkUid(uid);
		CheckUtils.checkCourseId(courseId);

		StudentCourseInfo studentCourseInfo = studentCourseInfoDao.getStudentCourseInfo(uid, courseId);
		if (studentCourseInfo == null) {
			throw new CustomException.NoSuchStudentCourseInfoException(courseId);
		}
		else {
			Course course = courseDao.getCourse(courseId);
			if (course == null) {
				throw new CustomException.NoSuchCourseException(courseId);
			}
			else {
				// 判断是否已经提交
				if (studentCourseInfo.getScore() >= 0) {
					throw new CustomException.ScoreCommittedException(studentCourseInfo.getId());
				}
				else {
					// 没有提交的话，查出所有单项分数
					List<StudentCourseEvaluate> item = evaluateDao.getStudentCourseEvaluates(uid, courseId);
					if (item == null) {
						throw new CustomException.EvaluateNotFinishException();
					}
					// 检查是否已评价完所有项并算出总分
					int sum = (int) getTotalScore(item);
					// 更新学生评价
					studentCourseInfo.setScore(sum);
					studentCourseInfo.setCompleteTime(new Timestamp(System.currentTimeMillis()));
					// 更新该课程的评价
					int count = course.getEvaluatedPersonCount();
					course.setEvaluatedPersonCount(count + 1);
					course.setScore((course.getScore() * count + sum) / (count + 1));
					// 更新该课程每个Item的评分
					updateTeacherCourseEvaluateItem(item, count);
					studentCourseInfoDao.update(studentCourseInfo);
					courseDao.update(course);
					return studentCourseInfo;
				}
			}

		}
	}

	public List<TeacherCourseEvaluate> getTeacherCourseEvaluates(int courseId) throws CustomException.BaseCustomException {
		CheckUtils.checkCourseId(courseId);
		return evaluateDao.getTeacherCourseEvaluatesByCourse(courseId);
	}

	public StudentCourseInfo commentCourse(int uid, int courseId, String comment) throws CustomException.BaseCustomException {
		CheckUtils.checkUid(uid);
		CheckUtils.checkCourseId(courseId);
		CheckUtils.checkComment(comment);
		StudentCourseInfo studentCourseInfo = studentCourseInfoDao.getStudentCourseInfo(uid, courseId);
		if (studentCourseInfo.getScore() >= 0) {
			studentCourseInfo.setComment(comment);
			studentCourseInfoDao.save(studentCourseInfo);
			return studentCourseInfo;
		}
		else {
			throw new CustomException.EvaluateNotFinishException();
		}
	}

	public List<StudentCourseInfo> getCompletedEvaluate(int courseId) throws InvalidCourseIdException {
		CheckUtils.checkCourseId(courseId);
		return studentCourseInfoDao.getCompletedEvaluate(courseId);
	}

	public Map<String, TeacherCourseEvaluate> getMaxAndMin(int courseId) throws InvalidCourseIdException {
		CheckUtils.checkCourseId(courseId);
		List<TeacherCourseEvaluate> items = evaluateDao.getTeacherCourseEvaluatesByCourse(courseId);
		if (items == null) {
			return null;
		}
		else {
			Map<String, TeacherCourseEvaluate> result = new HashMap<String, TeacherCourseEvaluate>(2);
			TeacherCourseEvaluate max = items.get(0);
			TeacherCourseEvaluate min = items.get(0);
			for (TeacherCourseEvaluate item : items) {
				if (item.getScore() > max.getScore()) {
					max = item;
				}
				else if (item.getScore() < max.getScore()) {
					min = item;
				}
			}
			result.put("max", max);
			result.put("min", min);
			return result;
		}
	}
	
	public StudentCourseInfo reply(int id, String reply) throws BaseCustomException{
		CheckUtils.checkStudentCourseInfoId(id);
		CheckUtils.checkComment(reply);
		StudentCourseInfo studentCourseInfo = studentCourseInfoDao.getStudentCourseInfo(id);
		if (studentCourseInfo.getScore() >= 0) {
			studentCourseInfo.setReply(reply);
			studentCourseInfo.setReplyTime(new Timestamp(System.currentTimeMillis()));
			studentCourseInfoDao.save(studentCourseInfo);
			return studentCourseInfo;
		}
		else {
			throw new CustomException.EvaluateNotFinishException();
		}
	}
}
