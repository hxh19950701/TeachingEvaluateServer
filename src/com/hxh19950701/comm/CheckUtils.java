package com.hxh19950701.comm;

import java.util.List;

import com.hxh19950701.comm.CustomException.InvaildCodeException;
import com.hxh19950701.comm.CustomException.InvaildCommentException;
import com.hxh19950701.comm.CustomException.InvaildCreateTypeException;
import com.hxh19950701.comm.CustomException.InvaildPersonCountException;
import com.hxh19950701.comm.CustomException.InvaildStudentCourseInfoIdException;
import com.hxh19950701.comm.CustomException.InvalidClazzIdException;
import com.hxh19950701.comm.CustomException.InvalidCourseIdException;
import com.hxh19950701.comm.CustomException.InvalidCourseNameException;
import com.hxh19950701.comm.CustomException.InvalidDepartmentIdException;
import com.hxh19950701.comm.CustomException.InvalidIdentityException;
import com.hxh19950701.comm.CustomException.InvalidItemIdException;
import com.hxh19950701.comm.CustomException.InvalidPasswordException;
import com.hxh19950701.comm.CustomException.InvalidPersonNameException;
import com.hxh19950701.comm.CustomException.InvalidSexException;
import com.hxh19950701.comm.CustomException.InvalidStudentIdException;
import com.hxh19950701.comm.CustomException.InvalidSubjectIdException;
import com.hxh19950701.comm.CustomException.InvalidTeacherIdException;
import com.hxh19950701.comm.CustomException.InvalidTermException;
import com.hxh19950701.comm.CustomException.InvalidUidException;
import com.hxh19950701.comm.CustomException.InvalidUsernameException;
import com.hxh19950701.comm.CustomException.InvalidYearException;
import com.hxh19950701.comm.CustomException.NoEvaluateTargetException;
import com.hxh19950701.pojos.EvaluateThirdTarget;

public class CheckUtils {

	private CheckUtils() {
		throw new UnsupportedOperationException();
	}

	private static boolean containOtherChar(String s) {
		for (int i = 0; i < s.length(); ++i) {
			if (!Character.isLetterOrDigit(s.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public static boolean isLegalUsername(String username) {
		return !TextUtils.isEmpty(username) && username.length() >= 6 && username.length() <= 16 && !containOtherChar(username);
	}

	public static boolean isLegalStudentId(String studentId) {
		return !TextUtils.isEmpty(studentId) && studentId.length() >= 6 && studentId.length() <= 16 && !containOtherChar(studentId);
	}

	public static boolean isLegalTeacherId(String teacherId) {
		return !TextUtils.isEmpty(teacherId) && teacherId.length() >= 3 && teacherId.length() <= 16 && !containOtherChar(teacherId);
	}

	public static boolean isLegalPassword(String password) {
		return !TextUtils.isEmpty(password) && password.length() == 32 && !containOtherChar(password);
	}

	public static boolean isLegalCourseName(String courseName) {
		return !TextUtils.isEmpty(courseName);
	}

	public static boolean isLegalPersonName(String personName) {
		return !TextUtils.isEmpty(personName);
	}

	public static boolean isLegalYear(int year) {
		return year >= 2000 && year <= 2020;
	}
	
	public static boolean isLegalCode(String code) {
		return !TextUtils.isEmpty(code) && code.length() == 8;
	}

	public static void checkUsername(String username) throws InvalidUsernameException {
		if (!CheckUtils.isLegalUsername(username)) {
			throw new CustomException.InvalidUsernameException(username);
		}
	}

	public static void checkPassword(String password) throws InvalidPasswordException {
		if (!CheckUtils.isLegalPassword(password)) {
			throw new CustomException.InvalidPasswordException();
		}
	}

	public static void checkIdentity(int identity) throws InvalidIdentityException {
		if (identity != Constant.IDENTITY_TEACHER
				&& identity != Constant.IDENTITY_STUDENT
				&& identity != Constant.IDENTITY_ADMINISTRATOR) {
			throw new CustomException.InvalidIdentityException(identity);
		}
	}

	public static void checkCourseId(int courseId) throws InvalidCourseIdException {
		if (courseId < 0) {
			throw new CustomException.InvalidCourseIdException(courseId);
		}
	}

	public static void checkUid(int uid) throws InvalidUidException {
		if (uid < 0) {
			throw new CustomException.InvalidUidException(uid);
		}
	}

	public static void checkCourseName(String courseName) throws InvalidCourseNameException {
		if (!CheckUtils.isLegalCourseName(courseName)) {
			throw new CustomException.InvalidCourseNameException(courseName);
		}
	}

	public static void checkYear(int year) throws InvalidYearException {
		if (!CheckUtils.isLegalYear(year)) {
			throw new CustomException.InvalidYearException(year);
		}
	}

	public static void checkTerm(int term) throws InvalidTermException {
		if (term != Constant.TERM_FIRST && term != Constant.TERM_SECOND) {
			throw new CustomException.InvalidTermException(term);
		}
	}

	public static void checkPersonCount(int personCount) throws InvaildPersonCountException {
		if (personCount <= 0) {
			throw new CustomException.InvaildPersonCountException();
		}
	}

	public static void checkDepartmentId(int departmentId) throws InvalidDepartmentIdException {
		if (departmentId < 0) {
			throw new CustomException.InvalidDepartmentIdException(departmentId);
		}
	}

	public static void checkSubjectId(int subjectId) throws InvalidSubjectIdException {
		if (subjectId < 0) {
			throw new CustomException.InvalidSubjectIdException(subjectId);
		}
	}

	public static void checkItemId(int itemId) throws InvalidItemIdException {
		if (itemId < 0) {
			throw new CustomException.InvalidItemIdException(itemId);
		}
	}

	public static void checkStudentId(String studentId) throws InvalidStudentIdException {
		if (!CheckUtils.isLegalStudentId(studentId)) {
			throw new CustomException.InvalidStudentIdException(studentId);
		}
	}

	public static void checkPersonName(String name) throws InvalidPersonNameException {
		if (!CheckUtils.isLegalPersonName(name)) {
			throw new CustomException.InvalidPersonNameException(name);
		}
	}

	public static void checkSex(int sex) throws InvalidSexException {
		if (sex != Constant.SEX_FEMALE && sex != Constant.SEX_MALE) {
			throw new CustomException.InvalidSexException(sex);
		}
	}

	public static void checkClazzId(int clazzId) throws InvalidClazzIdException {
		if (clazzId < 0) {
			throw new CustomException.InvalidClazzIdException(clazzId);
		}
	}

	public static void checkTeacherId(String teacherId) throws InvalidTeacherIdException {
		if (!CheckUtils.isLegalTeacherId(teacherId)) {
			throw new CustomException.InvalidTeacherIdException(teacherId);
		}
	}

	public static void checkEvaluateTarget(List<EvaluateThirdTarget> evaluateTarget) throws NoEvaluateTargetException {
		if (evaluateTarget == null) {
			throw new CustomException.NoEvaluateTargetException();
		}
	}

	public static void checkComment(String comment) throws InvaildCommentException {
		if (comment == null || comment.length() < 5 || comment.length() > 120) {
			throw new CustomException.InvaildCommentException();
		}
	}

	public static void checkCreateType(int createType) throws InvaildCreateTypeException {
		if (createType != Constant.CREATE_BY_CODE && createType != Constant.CREATE_BY_TEACHER && createType != Constant.CREATE_BY_ADMINISTRATOR) {
			throw new CustomException.InvaildCreateTypeException(createType);
		}
	}
	
	public static void checkStudentCourseInfoId(int id) throws InvaildStudentCourseInfoIdException {
		if (id <= 0) {
			throw new CustomException.InvaildStudentCourseInfoIdException(id);
		}
	}
	
	public static void checkCode(String code) throws InvaildCodeException {
		if (!isLegalCode(code)) {
			throw new CustomException.InvaildCodeException(code);
		}
	}
}
