package com.tao.linkman.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.tao.customer.domain.Customer;
import com.tao.linkman.domain.LinkMan;
@SuppressWarnings("all")
public class LinkManDaoImpl implements LinkManDao {
	
	HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void add(LinkMan linkMan) {
		this.hibernateTemplate.save(linkMan);
	}

	@Override
	public List<LinkMan> findAllLinkMan() {
		
		List<LinkMan> linkMans=(List<LinkMan>) hibernateTemplate.find("from LinkMan");
		return linkMans;
	}

	@Override
	public LinkMan findOne(int lid) {
		// TODO Auto-generated method stub
		List<LinkMan> linkMans=(List<LinkMan>) hibernateTemplate.find("from LinkMan where lid=?", lid);
		if(linkMans!=null && !linkMans.isEmpty()){
			return linkMans.get(0);
		}
		return null;
	}

	@Override
	public void edit(LinkMan linkMan) {
		// TODO Auto-generated method stub
		hibernateTemplate.update(linkMan);
	}

	@Override
	public void delete(LinkMan linkMan) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(linkMan);	
	}

	@Override
	public List<LinkMan> findByName(String name) {
		// TODO Auto-generated method stub
		
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		Criteria criteria=session.createCriteria(LinkMan.class);
		criteria.add(Restrictions.like("lkmName", "%"+name+"%"));
		List<LinkMan> linkMans=criteria.list();
		return linkMans;
	}

	@Override
	public List<LinkMan> findMoreCondition(LinkMan linkMan) {

		DetachedCriteria criteria=DetachedCriteria.forClass(LinkMan.class);
		criteria.add(Restrictions.like("lkmName", "%"+linkMan.getLkmName()+"%"));
		criteria.add(Restrictions.eq("customer.cid",linkMan.getCustomer().getCid()));
		List<LinkMan> linkMans=(List<LinkMan>) hibernateTemplate.findByCriteria(criteria);
		return linkMans;
	}
	
	
}
