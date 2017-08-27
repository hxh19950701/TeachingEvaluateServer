package com.hxh19950701.service;

import java.util.List;

import com.hxh19950701.comm.CheckUtils;
import com.hxh19950701.comm.Constant;
import com.hxh19950701.comm.CustomException;
import com.hxh19950701.comm.MD5Util;
import com.hxh19950701.dao.UserDao;
import com.hxh19950701.pojos.User;
import com.hxh19950701.service.UserService;

public class UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public boolean hasExist(String username) throws CustomException.BaseCustomException {
		CheckUtils.checkUsername(username);
		return userDao.getUser(username) != null;
	}

	public User registerStudentUser(String username, String password, int createType)
			throws CustomException.BaseCustomException {
		return registerUser(username, password, Constant.IDENTITY_STUDENT, createType);
	}

	public User registerTeacherUser(String username, String password, int createType)
			throws CustomException.BaseCustomException {
		return registerUser(username, password, Constant.IDENTITY_TEACHER, createType);
	}

	public User registerUser(String username, String password, int identity, int createType) throws CustomException.BaseCustomException {
		CheckUtils.checkUsername(username);
		CheckUtils.checkPassword(password);
		CheckUtils.checkIdentity(identity);

		if (userDao.getUser(username) != null) {
			throw new CustomException.UsernameExistException(username);
		}
		else {
			User user = new User(username, password, identity, createType);
			userDao.save(user);
			return user;
		}
	}

	public User getUser(int uid) throws CustomException.BaseCustomException {
		CheckUtils.checkUid(uid);
		return userDao.getUser(uid);
	}

	public User getUser(String username, String password) throws CustomException.BaseCustomException {
		CheckUtils.checkUsername(username);
		CheckUtils.checkPassword(password);

		User user = userDao.getUser(username);
		if (user == null) {
			throw new CustomException.NoSuchUsernameException(username);
		}
		else if (!user.isEnable()) {
			throw new CustomException.DisabledUserException(username);
		}
		else if (user.getPassword().equalsIgnoreCase(password)) {
			return user;
		}
		else {
			throw new CustomException.WrongPasswordException();
		}
	}

	public User updateUser(User user) {
		userDao.update(user);
		return user;
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	public User modifyEnable(int uid, boolean enable) throws CustomException.BaseCustomException {
		CheckUtils.checkUid(uid);
		User user = userDao.getUser(uid);
		if (user == null) {
			throw new CustomException.NoSuchUserException(uid);
		}
		else if (user.getIdentity() == Constant.IDENTITY_ADMINISTRATOR) {
			throw new CustomException.PermissionDeniedException();
		}
		if (user.isEnable() != enable) {
			user.setEnable(enable);
			userDao.save(user);
		}
		return user;
	}

	public User resetPassword(int uid) throws CustomException.BaseCustomException {
		CheckUtils.checkUid(uid);
		User user = userDao.getUser(uid);
		if (user == null) {
			throw new CustomException.NoSuchUserException(uid);
		}
		else if (user.getIdentity() == Constant.IDENTITY_ADMINISTRATOR) {
			throw new CustomException.PermissionDeniedException();
		}
		String password = MD5Util.encipher(user.getUsername());
		if (!user.getPassword().equalsIgnoreCase(password)) {
			user.setPassword(password);
			userDao.save(user);
		}
		return user;
	}

}
