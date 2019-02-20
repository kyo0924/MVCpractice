package com.rclass.member.model.vo;

import java.io.Serializable;
import java.sql.Date;

//서블릿상에서 주고받는 데이터가 있는 객체들은 Serializable(직렬화)을 해줘야함 - board, notice도 추가해줘야함
public class Member implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3765390024525231288L;
	
	private String userId;
	private String password;
	private String userName;
	private String gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String hobby;
	private Date enrollDate;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Member(String userId, String password, String userName, String gender, int age, String email, String phone, String address,
			String hobby, Date enrollDate) {
		super();
		this.userId = userId;
		this.password = password;
		this.userName=userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
	}
	
		
	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	@Override
	public String toString() {
		return "Member [userId=" + userId + ", password=" + password + ", gender=" + gender + ", age=" + age
				+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", hobby=" + hobby
				+ ", enrollDate=" + enrollDate + "]";
	}
	
}
