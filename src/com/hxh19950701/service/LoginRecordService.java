package com.hxh19950701.service;

import java.util.List;

import com.hxh19950701.comm.CheckUtils;
import com.hxh19950701.comm.CustomException;
import com.hxh19950701.comm.CustomException.InvalidUidException;
import com.hxh19950701.dao.LoginRecordDao;
import com.hxh19950701.dao.UserDao;
import com.hxh19950701.pojos.LoginRecord;
import com.hxh19950701.pojos.User;
import com.hxh19950701.service.LoginRecordService;

public class LoginRecordService {

	private UserDao userDao;
	private LoginRecordDao loginRecordDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setLoginRecordDao(LoginRecordDao loginRecordDao) {
		this.loginRecordDao = loginRecordDao;
	}

	public LoginRecord add(int uid, String ip) throws CustomException.BaseCustomException {
		CheckUtils.checkUid(uid);

		User user = userDao.getUser(uid);
		LoginRecord loginRecord = new LoginRecord(ip, user);
		loginRecordDao.save(loginRecord);
		return loginRecord;
	}

	public List<LoginRecord> getRecords(int uid) throws InvalidUidException {
		CheckUtils.checkUid(uid);
		return loginRecordDao.getRecords(uid);
	}
}
