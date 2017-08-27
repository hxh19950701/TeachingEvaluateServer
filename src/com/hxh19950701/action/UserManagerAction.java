package com.hxh19950701.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.hxh19950701.comm.CheckUtils;
import com.hxh19950701.comm.Constant;
import com.hxh19950701.comm.CustomException;
import com.hxh19950701.comm.CustomException.InvalidUidException;
import com.hxh19950701.comm.CustomException.NoOnlineUserException;
import com.hxh19950701.comm.CustomException.PermissionDeniedException;
import com.hxh19950701.pojos.LoginRecord;
import com.hxh19950701.pojos.User;
import com.hxh19950701.service.CodeService;
import com.hxh19950701.service.LoginRecordService;
import com.hxh19950701.service.UserService;
import com.opensymphony.xwork2.ActionContext;

public class UserManagerAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String newPassword;
	private String code = "";
	private int uid = -1;
	private int identity = -1;
	private boolean enable;

	private UserService userService;
	private CodeService codeService;
	private LoginRecordService loginRecordService;

	public void setIdentity(int identity) {
		this.identity = identity;
	}

	public void setLoginRecordService(LoginRecordService loginRecordService) {
		this.loginRecordService = loginRecordService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}
	
	/**
	 * 判断用户名是否存在
	 */
	public Object hasExist() throws CustomException.BaseCustomException {
		return userService.hasExist(username);
	}

	/**
	 * 新建用户
	 */
	public Object register() throws CustomException.BaseCustomException {
		int createType;
		try {
			switch (getOnlineUser().getIdentity()) {
				case Constant.IDENTITY_ADMINISTRATOR:
					createType = Constant.CREATE_BY_ADMINISTRATOR;
					break;
				case Constant.IDENTITY_TEACHER:
					createType = Constant.CREATE_BY_TEACHER;
					break;
				default:
					createType = Constant.CREATE_BY_CODE;
					break;
			}
		}
		catch (CustomException.NoOnlineUserException e) {
			createType = Constant.CREATE_BY_CODE;
		}
		if (createType != Constant.CREATE_BY_ADMINISTRATOR
				&& identity != Constant.IDENTITY_STUDENT) {
			// 非管理员用户只能创建学生用户
			throw new PermissionDeniedException();
		}
		if (createType == Constant.CREATE_BY_CODE) {
			codeService.checkEnable(code);
		}
		User user = userService.registerUser(username, password, identity, createType);
		return user;
	}

	@SuppressWarnings("unchecked")
	public Object login() throws CustomException.BaseCustomException {
		User user = userService.getUser(username, password);
		ActionContext.getContext().getSession().put("user", user);
		// 保存登录记录
		String ip = ServletActionContext.getRequest().getRemoteAddr();
		loginRecordService.add(user.getId(), ip);
		return user;
	}

	public Object logout() throws CustomException.BaseCustomException {
		User user = getOnlineUser();
		ActionContext.getContext().getSession().clear();
		return user;
	}

	public User modifyPassword() throws CustomException.BaseCustomException {
		CheckUtils.checkPassword(password);
		CheckUtils.checkPassword(newPassword);
		User user = getOnlineUser();
		if (user.getPassword().equalsIgnoreCase(password)) {
			user.setPassword(newPassword);
			userService.updateUser(user);
			logout();
		}
		else {
			throw new CustomException.WrongPasswordException();
		}
		return user;
	}

	public User resetPassword() throws CustomException.BaseCustomException {
		if (getOnlineUser().getIdentity() == Constant.IDENTITY_ADMINISTRATOR) {
			return userService.resetPassword(uid);
		}
		else {
			throw new CustomException.PermissionDeniedException();
		}
	}

	public User getUser() throws CustomException.BaseCustomException {
		if (getOnlineUser().getIdentity() == Constant.IDENTITY_ADMINISTRATOR) {
			return userService.getUser(uid);
		}
		else {
			throw new CustomException.PermissionDeniedException();
		}
	}

	public List<User> getAllUser() throws CustomException.BaseCustomException {
		if (getOnlineUser().getIdentity() == Constant.IDENTITY_ADMINISTRATOR) {
			return userService.getAllUsers();
		}
		else {
			throw new CustomException.PermissionDeniedException();
		}
	}

	public User modifyEnable() throws CustomException.BaseCustomException {
		if (getOnlineUser().getIdentity() == Constant.IDENTITY_ADMINISTRATOR) {
			return userService.modifyEnable(uid, enable);
		}
		else {
			throw new CustomException.PermissionDeniedException();
		}
	}

	public List<LoginRecord> getRecords() throws InvalidUidException, NoOnlineUserException, PermissionDeniedException {
		if (getOnlineUser().getIdentity() == Constant.IDENTITY_ADMINISTRATOR) {
			return loginRecordService.getRecords(uid);
		}
		else {
			throw new CustomException.PermissionDeniedException();
		}
	}
}
