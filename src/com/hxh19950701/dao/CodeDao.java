package com.hxh19950701.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hxh19950701.pojos.Code;

public class CodeDao extends HibernateDaoSupport {

	public void save(Code code) {
		getHibernateTemplate().save(code);
	}

	public List<Code> findCode(String code) {
		String hql = "from Code where code=? order by endTime desc";
		@SuppressWarnings("unchecked")
		List<Code> list = getHibernateTemplate().find(hql, code);
		return list;
	}
}
