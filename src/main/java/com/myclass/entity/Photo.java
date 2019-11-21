package com.myclass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "photos")
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name_photo")
	private String namePhoto;

	@Column(name = "url")
	private String url;

	@Column(name = "user_id")
	private int userId;

	@ManyToOne()
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	@JsonIgnoreProperties(value = {"images"})
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
	public Photo(int id, String namePhoto, String url, int userId) {

		this.id = id;
		this.namePhoto = namePhoto;
		this.url = url;
		this.userId = userId;
		
	}

	public Photo(String namePhoto, String url, int userId, User user) {

		this.namePhoto = namePhoto;
		this.url = url;
		this.userId = userId;
		this.user = user;
	}
	
	public Photo(String namePhoto, String url, int userId) {

		this.namePhoto = namePhoto;
		this.url = url;
		this.userId = userId;
		
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

	@Override
	public String toString() {
		return "Photo [id=" + id + ", namePhoto=" + namePhoto + ", url=" + url + ", userId=" + userId + ", user=" + user
				+ "]";
	}

}
