package com.ty.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Bank {
   @Id
	private int bid;
	private String bname;
	private String branch;
	@OneToMany(cascade = CascadeType.PERSIST,mappedBy = "bank")
	List<Account> acount;
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public List<Account> getAcount() {
		return acount;
	}
	public void setAcount(List<Account> acount) {
		this.acount = acount;
	}
	
}
