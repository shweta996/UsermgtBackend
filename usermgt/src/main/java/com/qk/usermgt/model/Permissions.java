package com.qk.usermgt.model;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Permissions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long permissionId;
	
	private String permissionName;
	
	private boolean add;
	
	private boolean modify;
	
	private boolean delete;
	
	private boolean read;
	
	//@JsonIgnoreProperties(value = "permissions")
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	public Permissions()
	{
		
	}

	public Permissions(Long permissionId, String permissionName, boolean add, boolean modify, boolean delete,
			boolean read, User user) {
		this.permissionId = permissionId;
		this.permissionName = permissionName;
		this.add = add;
		this.modify = modify;
		this.delete = delete;
		this.read = read;
		this.user = user;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public boolean isAdd() {
		return add;
	}

	public void setAdd(boolean add) {
		this.add = add;
	}

	public boolean isModify() {
		return modify;
	}

	public void setModify(boolean modify) {
		this.modify = modify;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Permissions [permissionId=" + permissionId + ", permissionName=" + permissionName + ", add=" + add
				+ ", modify=" + modify + ", delete=" + delete + ", read=" + read + ", user=" + user + "]";
	}
	
	

}
