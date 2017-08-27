package com.hxh19950701.comm;

public class CustomException {

	private CustomException() {
		throw new UnsupportedOperationException();
	}

	public static abstract class BaseCustomException extends Exception {
		private static final long serialVersionUID = 1L;

		public abstract int getErrorCode();

		public abstract String getErrorMessage();
	}

	public static class NoSuchUsernameException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private String data;

		public NoSuchUsernameException(String data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -101;
		}

		@Override
		public String getErrorMessage() {
			return "用户名" + data + "不存在。";
		}
	}

	public static class WrongPasswordException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		@Override
		public int getErrorCode() {
			return -102;
		}

		@Override
		public String getErrorMessage() {
			return "密码不正确。";
		}
	}

	public static class InvalidUsernameException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private String data;

		public InvalidUsernameException(String data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -103;
		}

		@Override
		public String getErrorMessage() {
			return "用户名" + data + "格式不正确。";
		}
	}

	public static class InvalidPasswordException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		@Override
		public int getErrorCode() {
			return -104;
		}

		@Override
		public String getErrorMessage() {
			return "密码格式不正确。";
		}
	}

	public static class InvalidIdentityException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public InvalidIdentityException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -105;
		}

		@Override
		public String getErrorMessage() {
			return "无效的用户身份标识符" + data + "。";
		}
	}

	public static class InvalidSexException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public InvalidSexException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -106;
		}

		@Override
		public String getErrorMessage() {
			return "无效的性别标识符" + data + "。";
		}
	}

	public static class InvalidCourseIdException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public InvalidCourseIdException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -107;
		}

		@Override
		public String getErrorMessage() {
			return "无效的课程标识符" + data + "。";
		}
	}

	public static class InvalidUidException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public InvalidUidException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -108;
		}

		@Override
		public String getErrorMessage() {
			return "无效的用户识符" + data + "。";
		}
	}

	public static class AddCourseRepeatedlyException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public AddCourseRepeatedlyException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -109;
		}

		@Override
		public String getErrorMessage() {
			return "你已添加编号为" + data + "的课程，不能重复添加。";
		}
	}

	public static class NoSuchCourseException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public NoSuchCourseException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -110;
		}

		@Override
		public String getErrorMessage() {
			return "编号为" + data + "的课程不存在。";
		}
	}

	public static class InvalidYearException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public InvalidYearException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -111;
		}

		@Override
		public String getErrorMessage() {
			return "无效的年份：" + data + "。";
		}
	}

	public static class NoSuchStudentException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public NoSuchStudentException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -112;
		}

		@Override
		public String getErrorMessage() {
			return "编号为" + data + "的学生不存在。";
		}
	}

	public static class NoSuchTeacherException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public NoSuchTeacherException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -113;
		}

		@Override
		public String getErrorMessage() {
			return "编号为" + data + "的教师不存在。";
		}
	}

	public static class PermissionDeniedException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		@Override
		public int getErrorCode() {
			return -114;
		}

		@Override
		public String getErrorMessage() {
			return "你无权进行此操作。";
		}
	}

	public static class InvalidCourseNameException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private String data;

		public InvalidCourseNameException(String data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -115;
		}

		@Override
		public String getErrorMessage() {
			return "课程名" + data + "格式不正确。";
		}
	}

	public static class InvalidTermException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public InvalidTermException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -116;
		}

		@Override
		public String getErrorMessage() {
			return "无效的学期标识符：" + data + "。";
		}
	}

	public static class InvalidItemIdException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public InvalidItemIdException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -117;
		}

		@Override
		public String getErrorMessage() {
			return "无效的评价条目标识符：" + data + "。";
		}
	}

	public static class ScoreCommittedException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public ScoreCommittedException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -118;
		}

		@Override
		public String getErrorMessage() {
			return "编号为" + data + "的学生评价已经提交。";
		}
	}

	public static class InvalidScoreException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private float data;

		public InvalidScoreException(float data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -119;
		}

		@Override
		public String getErrorMessage() {
			return "无效的分数格式：" + data + "。";
		}
	}

	public static class NoSuchStudentCourseInfoException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public NoSuchStudentCourseInfoException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -120;
		}

		@Override
		public String getErrorMessage() {
			return "课程编号为" + data + "学生课程信息不存在。";
		}
	}

	public static class NoEvaluateTargetException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		@Override
		public int getErrorCode() {
			return -121;
		}

		@Override
		public String getErrorMessage() {
			return "没有评价条目。";
		}
	}

	public static class EvaluateNotFinishException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		@Override
		public int getErrorCode() {
			return -122;
		}

		@Override
		public String getErrorMessage() {
			return "该评价还没有完成，不能提交。";
		}
	}

	public static class InvalidStudentIdException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private String data;

		public InvalidStudentIdException(String data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -123;
		}

		@Override
		public String getErrorMessage() {
			return "学号" + data + "格式不正确。";
		}
	}

	public static class InvalidTeacherIdException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private String data;

		public InvalidTeacherIdException(String data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -124;
		}

		@Override
		public String getErrorMessage() {
			return "教师工号" + data + "格式不正确。";
		}
	}

	public static class InvalidPersonNameException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private String data;

		public InvalidPersonNameException(String data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -125;
		}

		@Override
		public String getErrorMessage() {
			return "姓名" + data + "不正确。";
		}
	}

	public static class InvalidClazzIdException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public InvalidClazzIdException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -126;
		}

		@Override
		public String getErrorMessage() {
			return "无效的班级编号：" + data + "。";
		}
	}

	public static class NoSuchClazzException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public NoSuchClazzException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -127;
		}

		@Override
		public String getErrorMessage() {
			return "编号为" + data + "班级不存在。";
		}
	}

	public static class UsernameExistException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private String data;

		public UsernameExistException(String data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -128;
		}

		@Override
		public String getErrorMessage() {
			return "用户名" + data + "已被注册。";
		}
	}

	public static class NoOnlineUserException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		@Override
		public int getErrorCode() {
			return -129;
		}

		@Override
		public String getErrorMessage() {
			return "你还没有登录或登录已经过期。";
		}
	}

	public static class InvalidDepartmentIdException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public InvalidDepartmentIdException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -130;
		}

		@Override
		public String getErrorMessage() {
			return "系部标识符" + data + "不正确。";
		}
	}

	public static class InvalidSubjectIdException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public InvalidSubjectIdException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -131;
		}

		@Override
		public String getErrorMessage() {
			return "专业标识符" + data + "不正确。";
		}
	}

	public static class StudentIdExistException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private String data;

		public StudentIdExistException(String data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -132;
		}

		@Override
		public String getErrorMessage() {
			return "学号" + data + "已被注册。";
		}
	}

	public static class StudentExistException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		@Override
		public int getErrorCode() {
			return -133;
		}

		@Override
		public String getErrorMessage() {
			return "该学生已存在。";
		}
	}

	public static class NoSuchActionException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private String data;

		public NoSuchActionException(String data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -134;
		}

		@Override
		public String getErrorMessage() {
			return "ACTION：" + data + "不存在。";
		}
	}

	public static class InvaildPersonCountException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		@Override
		public int getErrorCode() {
			return -135;
		}

		@Override
		public String getErrorMessage() {
			return "人数必须大于0。";
		}
	}

	public static class PersonCountLimitExceedException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		@Override
		public int getErrorCode() {
			return -136;
		}

		@Override
		public String getErrorMessage() {
			return "已超出人数上限";
		}
	}

	public static class InvaildCommentException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		@Override
		public int getErrorCode() {
			return -137;
		}

		@Override
		public String getErrorMessage() {
			return "非法的评论，评论字数至少10个字，最长120字。";
		}
	}

	public static class InvaildCreateTypeException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public InvaildCreateTypeException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -138;
		}

		@Override
		public String getErrorMessage() {
			return "无效的创建方式：" + data + "。";
		}
	}

	public static class DisabledUserException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private String data;

		public DisabledUserException(String data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -139;
		}

		@Override
		public String getErrorMessage() {
			return "用户" + data + "尚未启用，请联系管理员。";
		}
	}

	public static class NoSuchUserException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public NoSuchUserException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -140;
		}

		@Override
		public String getErrorMessage() {
			return "Uid为" + data + "的用户不存在。";
		}
	}

	public static class InvaildStudentCourseInfoIdException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public InvaildStudentCourseInfoIdException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -141;
		}

		@Override
		public String getErrorMessage() {
			return "无效的学生课程信息ID：" + data + "。";
		}
	}

	public static class EmptyNameException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		@Override
		public int getErrorCode() {
			return -142;
		}

		@Override
		public String getErrorMessage() {
			return "名称为空";
		}
	}

	public static class NoSuchDepartmentException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public NoSuchDepartmentException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -143;
		}

		@Override
		public String getErrorMessage() {
			return "id为" + data + "的系部不存在。";
		}
	}

	public static class NoSuchSubjectException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private int data;

		public NoSuchSubjectException(int data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -144;
		}

		@Override
		public String getErrorMessage() {
			return "id为" + data + "的专业不存在。";
		}
	}

	public static class InvaildCodeException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private String data;

		public InvaildCodeException(String data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -144;
		}

		@Override
		public String getErrorMessage() {
			return "无效的注册码：" + data;
		}
	}
	
	public static class NoSuchCodeException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private String data;

		public NoSuchCodeException(String data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -145;
		}

		@Override
		public String getErrorMessage() {
			return "不存在的注册码：" + data;
		}
	}
	
	public static class CodeExpiredException extends BaseCustomException {
		private static final long serialVersionUID = 1L;

		private String data;

		public CodeExpiredException(String data) {
			super();
			this.data = data;
		}

		@Override
		public int getErrorCode() {
			return -146;
		}

		@Override
		public String getErrorMessage() {
			return "已过期的注册码：" + data;
		}
	}
}
