package com.tao.visit.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.tao.linkman.domain.LinkMan;
import com.tao.visit.domain.Visit;

public class VisitDaoImpl implements VisitDao{
	HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void add(Visit visit) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(visit);
	}

	@Override
	public List<Visit> findAll() {
		// TODO Auto-generated method stub
		return (List<Visit>) hibernateTemplate.find("from Visit");
		
	}

	@Override
	public List<Visit> findMoreCondition(Visit visit) {
		DetachedCriteria criteria=DetachedCriteria.forClass(Visit.class);
		if(visit.getCustomer().getCid()>0){
			criteria.add(Restrictions.eq("customer.cid", visit.getCustomer().getCid()));
		}
		if(visit.getUser().getUid()>0){
			criteria.add(Restrictions.eq("user.uid",visit.getUser().getUid()));
		}
		
		List<Visit> visits=(List<Visit>) hibernateTemplate.findByCriteria(criteria);
		return visits;
	}
	
}
