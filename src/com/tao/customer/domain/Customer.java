package com.tao.customer.domain;

import java.util.HashSet;
import java.util.Set;

import com.tao.linkman.domain.LinkMan;
import com.tao.utils.Dict;
import com.tao.visit.domain.Visit;

public class Customer {
	private Integer cid;
	private String custName;
	private String custSource;
	private String custPhone;
	private String custMobile;
	
	private Dict dict;

	public Dict getDict() {
		return dict;
	}
	public void setDict(Dict dict) {
		this.dict = dict;
	}


	private Set<Visit> cvisit=new HashSet<Visit>();
	public Set<Visit> getCvisit() {
		return cvisit;
	}
	public void setCvisit(Set<Visit> cvisit) {
		this.cvisit = cvisit;
	}
	
	
	private Set<LinkMan> linkMan;
	public Set<LinkMan> getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(Set<LinkMan> linkMan) {
		this.linkMan = linkMan;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustSource() {
		return custSource;
	}
	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustMobile() {
		return custMobile;
	}
	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}
	
	
	
	
}
