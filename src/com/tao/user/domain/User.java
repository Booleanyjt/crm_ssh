package com.tao.user.domain;

import java.util.HashSet;
import java.util.Set;

import com.tao.visit.domain.Visit;

public class User {
	private Integer uid;
	private String username;
	private String password;
	private String address;
	
	private Set<Visit> uvisit=new HashSet<Visit>();
	
	
	public Set<Visit> getUvisit() {
		return uvisit;
	}
	public void setUvisit(Set<Visit> uvisit) {
		this.uvisit = uvisit;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
