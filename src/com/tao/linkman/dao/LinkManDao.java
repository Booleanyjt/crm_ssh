package com.tao.linkman.dao;

import java.util.List;

import com.tao.linkman.domain.LinkMan;

public interface LinkManDao {

	void add(LinkMan linkMan);

	List<LinkMan> findAllLinkMan();

	LinkMan findOne(int lid);

	void edit(LinkMan linkMan);

	void delete(LinkMan linkMan);

	List<LinkMan> findByName(String name);

	List<LinkMan> findMoreCondition(LinkMan linkMan);

}
