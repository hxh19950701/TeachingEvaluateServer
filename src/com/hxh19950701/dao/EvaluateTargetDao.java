package com.hxh19950701.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hxh19950701.dao.EvaluateTargetDao;
import com.hxh19950701.pojos.EvaluateFirstTarget;
import com.hxh19950701.pojos.EvaluateSecondTarget;
import com.hxh19950701.pojos.EvaluateThirdTarget;

public class EvaluateTargetDao extends HibernateDaoSupport{

	public EvaluateFirstTarget getEvaluateFirstTarget(int id) {
		String hql = "from EvaluateFirstTarget where id=?";
		@SuppressWarnings("unchecked")
		List<EvaluateFirstTarget> list = getHibernateTemplate().find(hql, id);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public EvaluateFirstTarget getEvaluateFirstTarget(String name) {
		String hql = "from EvaluateFirstTarget where name=?";
		@SuppressWarnings("unchecked")
		List<EvaluateFirstTarget> list = getHibernateTemplate().find(hql, name);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public List<EvaluateFirstTarget> getAllEvaluateFirstTargets() {
		String hql = "from EvaluateFirstTarget";
		@SuppressWarnings("unchecked")
		List<EvaluateFirstTarget> list = getHibernateTemplate().find(hql);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public EvaluateSecondTarget getEvaluateSecondTarget(int id) {
		String hql = "from EvaluateSecondTarget where id=?";
		@SuppressWarnings("unchecked")
		List<EvaluateSecondTarget> list = getHibernateTemplate().find(hql, id);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public EvaluateSecondTarget getEvaluateSecondTarget(String name) {
		String hql = "from EvaluateSecondTarget where name=?";
		@SuppressWarnings("unchecked")
		List<EvaluateSecondTarget> list = getHibernateTemplate().find(hql, name);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public List<EvaluateSecondTarget> getAllEvaluateSecondTargets() {
		String hql = "from EvaluateSecondTarget";
		@SuppressWarnings("unchecked")
		List<EvaluateSecondTarget> list = getHibernateTemplate().find(hql);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public List<EvaluateSecondTarget> getAllEvaluateSecondTargets(int firstTargetId) {
		String hql = "from EvaluateSecondTarget where firstTarget.id=";
		@SuppressWarnings("unchecked")
		List<EvaluateSecondTarget> list = getHibernateTemplate().find(hql, firstTargetId);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public EvaluateThirdTarget getEvaluateThirdTarget(int id) {
		String hql = "from EvaluateThirdTarget where id=?";
		@SuppressWarnings("unchecked")
		List<EvaluateThirdTarget> list = getHibernateTemplate().find(hql, id);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public EvaluateThirdTarget getEvaluateThirdTarget(String name) {
		String hql = "from EvaluateThirdTarget where name=?";
		@SuppressWarnings("unchecked")
		List<EvaluateThirdTarget> list = getHibernateTemplate().find(hql, name);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public List<EvaluateThirdTarget> getAllEvaluateThirdTargets() {
		String hql = "from EvaluateThirdTarget";
		@SuppressWarnings("unchecked")
		List<EvaluateThirdTarget> list = getHibernateTemplate().find(hql);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public List<EvaluateThirdTarget> getAllEvaluateThirdTargetsByFirstTarget(int firstTargetId) {
		String hql = "from EvaluateThirdTarget where secondTarget.firstTarge.id=";
		@SuppressWarnings("unchecked")
		List<EvaluateThirdTarget> list = getHibernateTemplate().find(hql, firstTargetId);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public List<EvaluateThirdTarget> getAllEvaluateThirdTargetsBySecondTarget(int secondTargetId) {
		String hql = "from EvaluateThirdTarget where secondTarget.id=";
		@SuppressWarnings("unchecked")
		List<EvaluateThirdTarget> list = getHibernateTemplate().find(hql, secondTargetId);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

}
