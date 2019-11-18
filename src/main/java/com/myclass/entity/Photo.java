package com.myclass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "image")
public class Photo {

	@Id
	@GeneratedValue
	private int id;

	private String namePhoto;

	private String url;

	@Column(name = "user_id")
	private int userId;

	@ManyToOne()
	@JoinColumn(name = "id_user", insertable = false, updatable = false)
	private User user;

	public Photo() {
		// TODO Auto-generated constructor stub
	}

	public Photo(int id, String namePhoto, String url, int userId, User user) {

		this.id = id;
		this.namePhoto = namePhoto;
		this.url = url;
		this.userId = userId;
		this.user = user;
	}

	public Photo(String namePhoto, String url, int userId, User user) {

		this.namePhoto = namePhoto;
		this.url = url;
		this.userId = userId;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	

	public String getNamePhoto() {
		return namePhoto;
	}

	public void setNamePhoto(String namePhoto) {
		this.namePhoto = namePhoto;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
