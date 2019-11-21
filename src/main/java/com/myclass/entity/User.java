package com.myclass.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "email không được bỏ trống")
	private String email;

	@NotBlank(message = "name không được bỏ trống")
	private String name;

	@NotBlank(message = "password không được bỏ trống")
	private String password;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE, CascadeType.REFRESH,
			CascadeType.PERSIST })
	@JsonIgnoreProperties(value = {"user"})
	private List<Photo> images;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int id, @NotBlank(message = "email không được bỏ trống") String email,
			@NotBlank(message = "name không được bỏ trống") String name,
			@NotBlank(message = "password không được bỏ trống") String password, List<Photo> images) {

		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.images = images;
	}

	public User(@NotBlank(message = "email không được bỏ trống") String email,
			@NotBlank(message = "name không được bỏ trống") String name,
			@NotBlank(message = "password không được bỏ trống") String password, List<Photo> images) {

		this.email = email;
		this.name = name;
		this.password = password;
		this.images = images;
	}
	
	public User(int id, @NotBlank(message = "email không được bỏ trống") String email,
			@NotBlank(message = "name không được bỏ trống") String name,
			@NotBlank(message = "password không được bỏ trống") String password) {

		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		
	}

	public User(@NotBlank(message = "email không được bỏ trống") String email,
			@NotBlank(message = "name không được bỏ trống") String name,
			@NotBlank(message = "password không được bỏ trống") String password) {

		this.email = email;
		this.name = name;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Photo> getImages() {
		return images;
	}

	public void setImages(List<Photo> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + "]";
	}

}
