package com.myclass.dto;

public class LoginUserDto {

	private String email;

	private String name;

	private String password;

	public LoginUserDto() {
		// TODO Auto-generated constructor stub
	}

	public LoginUserDto(String email, String name, String password) {

		this.email = email;
		this.name = name;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "LoginUserDto [email=" + email + ", name=" + name + ", password=" + password + "]";
	}


}
