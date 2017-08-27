package com.hxh19950701.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hxh19950701.comm.Constant;
import com.hxh19950701.pojos.User;

public class UserDao extends HibernateDaoSupport {

	public void save(User user) {
		user.notifyCreated();
		getHibernateTemplate().save(user);
	}

	public void update(User user) {
		user.notifyUpdated();
		getHibernateTemplate().update(user);
	}

	public User getUser(String username) {
		String hql = "from User where username=?";
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) getHibernateTemplate().find(hql, username);
		if (list == null || list.size() == 0) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	public List<User> getAllUsers() {
		String hql = "from User";
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) getHibernateTemplate().find(hql);
		if (list == null || list.size() == 0) {
			return null;
		}
		else {
			return list;
		}
	}
	
	public List<User> getAllUser(int identity){
		String hql = "from User where identity=?";
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) getHibernateTemplate().find(hql, identity);
		if (list == null || list.size() == 0) {
			return null;
		}
		else {
			return list;
		}
	}

	public User getUser(int id) {
		String hql = "from User where id=?";
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) getHibernateTemplate().find(hql, id);
		if (list == null || list.size() == 0) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	public List<User> getAllTeacherUsers(int page) {
		String hql = "from User where identity=1 limit ?, ?";
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) getHibernateTemplate()
				.find(hql, new int[] { page * Constant.ITEM_COUNT, Constant.ITEM_COUNT });
		if (list == null || list.size() == 0) {
			return null;
		}
		else {
			return list;
		}
	}

	public List<User> getAllStudentUsers(int page) {
		String hql = "from User where identity=0 limit ?, ?";
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) getHibernateTemplate()
				.find(hql, new int[] { page * Constant.ITEM_COUNT, Constant.ITEM_COUNT });
		if (list == null || list.size() == 0) {
			return null;
		}
		else {
			return list;
		}
	}

	public int getAllUsersCount() {
		String hql = "select count(id) from User";
		return (Integer) getHibernateTemplate().find(hql).get(0);
	}

	public int getAllTeacherUsersCount() {
		String hql = "select count(id) from User where identity=1";
		return (Integer) getHibernateTemplate().find(hql).get(0);
	}

	public int getAllStudentUsersCount() {
		String hql = "select count(id) from User where identity=0";
		return (Integer) getHibernateTemplate().find(hql).get(0);
	}

}
