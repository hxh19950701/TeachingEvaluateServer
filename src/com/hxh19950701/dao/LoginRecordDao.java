package com.hxh19950701.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hxh19950701.comm.Constant;
import com.hxh19950701.dao.LoginRecordDao;
import com.hxh19950701.pojos.LoginRecord;

public class LoginRecordDao extends HibernateDaoSupport{

	public void save(LoginRecord loginRecord) {
		getHibernateTemplate().save(loginRecord);
	}

	public List<LoginRecord> getRecords(int userId) {
		String hql = "from LoginRecord where user.id=? order by time desc";
		@SuppressWarnings("unchecked")
		List<LoginRecord> list = getHibernateTemplate().find(hql,
				new Integer[] { userId});
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}
	
	public List<LoginRecord> getRecords(int userId, int page) {
		String hql = "from LoginRecord where user.id=? limit ?,?";
		@SuppressWarnings("unchecked")
		List<LoginRecord> list = getHibernateTemplate().find(hql,
				new int[] { userId, page * Constant.ITEM_COUNT, Constant.ITEM_COUNT });
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public List<LoginRecord> getRecords(String ip, int page) {
		String hql = "from LoginRecord where ip=? limit ?,?";
		@SuppressWarnings("unchecked")
		List<LoginRecord> list = getHibernateTemplate().find(hql,
				new Object[] { ip, page * Constant.ITEM_COUNT, Constant.ITEM_COUNT });
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public List<LoginRecord> getRecords(Timestamp start, Timestamp end, int page) {
		String hql = "from LoginRecord where time>? and time<? limit ?,?";
		@SuppressWarnings("unchecked")
		List<LoginRecord> list = getHibernateTemplate().find(hql,
				new Object[] { start, end, page * Constant.ITEM_COUNT, Constant.ITEM_COUNT });
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public List<LoginRecord> getRecords(int userId, Timestamp start, Timestamp end, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LoginRecord> getRecords(String ip, Timestamp start, Timestamp end, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LoginRecord> getRecords(int userId, String ip, Timestamp start, Timestamp end,
			int page) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getRecordsCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getRecordsCount(int userId) {
		
		return 0;
	}

	public int getRecordsCount(String ip) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getRecordsCount(Timestamp start, Timestamp end) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getRecordsCount(int userId, Timestamp start, Timestamp end) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getRecordsCount(String ip, Timestamp start, Timestamp end) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getRecordsCount(int userId, String ip, Timestamp start, Timestamp end) {
		// TODO Auto-generated method stub
		return 0;
	}

	public LoginRecord getRecord(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
