package com.qk.usermgt.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qk.usermgt.model.Permissions;

public class UserDto {
	
private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private Date dateOfBirth;
	
	private String gender;
	
	private String country;
	
	private String contact;
	
	
	private String email;
	
	private String address;
	
	private String username;
	
	private String password;
	
	private String userRole;
	
private List<Permissions> permission = new ArrayList<Permissions>();
	
	public UserDto() {
		
	}

	public UserDto(String firstName, String middleName, String lastName, Date dateOfBirth, String gender,
			String country, String contact, String email, String address, String username, String password,
			String userRole, List<Permissions> permission) {
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
		this.permission = permission;
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

	public List<Permissions> getPermission() {
		return permission;
	}

	public void setPermission(List<Permissions> permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "UserDto [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", country=" + country + ", contact="
				+ contact + ", email=" + email + ", address=" + address + ", username=" + username + ", password="
				+ password + ", userRole=" + userRole + ", permission=" + permission + "]";
	}

	
	
	

}
