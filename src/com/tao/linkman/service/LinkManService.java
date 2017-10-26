package com.tao.linkman.service;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tao.linkman.dao.LinkManDao;
import com.tao.linkman.domain.LinkMan;

@Transactional
public class LinkManService {
	
	LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	public void add(LinkMan linkMan) {
		// TODO Auto-generated method stub
		this.linkManDao.add(linkMan);
	}

	public List<LinkMan> list() {
		// TODO Auto-generated method stub
		
		return linkManDao.findAllLinkMan();
	}

	public LinkMan finOne(int lid) {
		// TODO Auto-generated method stub
		return linkManDao.findOne(lid);
	}

	public void edit(LinkMan linkMan) {
		// TODO Auto-generated method stub
		linkManDao.edit(linkMan);
	}

	public void delete(LinkMan linkMan) {
		// TODO Auto-generated method stub
		linkManDao.delete(linkMan);
	}

	public List<LinkMan> findByName(String name) {
		// TODO Auto-generated method stub
		
		return linkManDao.findByName(name);
	}

	public List<LinkMan> findMoreCondition(LinkMan linkMan) {
		// TODO Auto-generated method stub
		return linkManDao.findMoreCondition(linkMan);
	}
	
	
}
