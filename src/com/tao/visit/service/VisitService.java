package com.tao.visit.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.regexp.internal.recompile;
import com.tao.visit.dao.VisitDao;
import com.tao.visit.domain.Visit;

@Transactional
public class VisitService {
	VisitDao visitDao;

	public void setVisitDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}

	public void add(Visit visit) {
		// TODO Auto-generated method stub
		visitDao.add(visit);
	}

	public List<Visit> findAll() {
		// TODO Auto-generated method stub
		return visitDao.findAll();
	}

	public List<Visit> findMoreCondition(Visit visit) {
		// TODO Auto-generated method stub
		return visitDao.findMoreCondition(visit);
	}
	
}
