package com.example.demodanei.entity;

import java.io.Serializable;
import java.util.Date;

abstract class BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String createdUser;//创建人
    private Date createdTime;//创建时间
    private String modifiedUser;//修改人
    private Date modifiedTime;//修改时间
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
    

}
