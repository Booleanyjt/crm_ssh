package com.tao.visit.dao;

import java.util.List;

import com.tao.visit.domain.Visit;

public interface VisitDao {

	void add(Visit visit);

	List<Visit> findAll();

	List<Visit> findMoreCondition(Visit visit);

}
