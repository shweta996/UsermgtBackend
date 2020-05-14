package com.qk.usermgt.model;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.sun.istack.NotNull;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@NotNull
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	@NotNull
	private Date dateOfBirth;
	
	@NotNull
	private String gender;
	
	@NotNull
	private String country;
	
	private String contact;
	
	@NotNull
	private String email;
	
	@NotNull
	private String address;
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	@NotNull
	private String userRole;
	
	private String profilePicture;
	
	private int age;
	
	private boolean activationStatus;
	
	private boolean onlineStatus = false;
	
	private Date createTime;
	
	private Date modifiedTime;
	
	private Date lastLogin;
	
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)	
	private List<Permissions> permissions = new ArrayList<Permissions>();
	
	@OneToOne(cascade = CascadeType.ALL)
	private Authentication authentication;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<LoginHistory> loginList = new ArrayList<LoginHistory>();
	
	private boolean verify;
	
	public User() {}

	public User(String id, String firstName, String middleName, String lastName, Date dateOfBirth, String gender,
			String country, String contact, String email, String address, String username, String password,
			String userRole, String profilePicture, int age, boolean activationStatus, boolean onlineStatus,
			Date createTime, Date modifiedTime, Date lastLogin, List<Permissions> permissions,
			Authentication authentication, List<LoginHistory> loginList,boolean verify) {
		
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.country = country;
		this.contact = contact;
		this.email = email;
		this.address = address;
		this.username = username;
		this.password = password;
		this.userRole = userRole;
		this.profilePicture = profilePicture;
		this.age = age;
		this.activationStatus = activationStatus;
		this.onlineStatus = onlineStatus;
		this.createTime = createTime;
		this.modifiedTime = modifiedTime;
		this.lastLogin = lastLogin;
		this.permissions = permissions;
		this.authentication = authentication;
		this.loginList = loginList;
		this.verify = verify;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}

	public boolean isOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(boolean onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public List<Permissions> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permissions> permissions) {
		this.permissions = permissions;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	public List<LoginHistory> getLoginList() {
		return loginList;
	}

	public void setLoginList(List<LoginHistory> loginList) {
		this.loginList = loginList;
	}
	

	public boolean isVerify() {
		return verify;
	}

	public void setVerify(boolean verify) {
		this.verify = verify;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", country=" + country + ", contact="
				+ contact + ", email=" + email + ", address=" + address + ", username=" + username + ", password="
				+ password + ", userRole=" + userRole + ", profilePicture=" + profilePicture + ", age=" + age
				+ ", activationStatus=" + activationStatus + ", onlineStatus=" + onlineStatus + ", createTime="
				+ createTime + ", modifiedTime=" + modifiedTime + ", lastLogin=" + lastLogin + ", permissions="
				+ permissions + ", authentication=" + authentication + ", loginList=" + loginList + ", verify=" + verify
				+ "]";
	}
	
	

}
