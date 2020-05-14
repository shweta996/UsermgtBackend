package com.qk.usermgt.model;

import java.util.Date;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class LoginHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loginHistoryId;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginDate;
	
	//@JsonIgnoreProperties(value = "loginList")
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	public LoginHistory() {
		
	}

	public LoginHistory(Long loginHistoryId, Date loginDate, User user) {
	
		this.loginHistoryId = loginHistoryId;
		this.loginDate = loginDate;
		this.user = user;
	}

	public Long getLoginHistoryId() {
		return loginHistoryId;
	}

	public void setLoginHistoryId(Long loginHistoryId) {
		this.loginHistoryId = loginHistoryId;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "LoginHistory [loginHistoryId=" + loginHistoryId + ", loginDate=" + loginDate + ", user=" + user + "]";
	}
	
	

}
