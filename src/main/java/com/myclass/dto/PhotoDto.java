package com.myclass.dto;

public class PhotoDto {
	private String namePhoto;
	private String url;
	
	public PhotoDto() {
		// TODO Auto-generated constructor stub
	}

	public PhotoDto(String namePhoto, String url) {
		
		this.namePhoto = namePhoto;
		this.url = url;
	}

	public String getNamePhoto() {
		return namePhoto;
	}

	public void setNamePhoto(String namePhoto) {
		this.namePhoto = namePhoto;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "PhotoDto [namePhoto=" + namePhoto + ", url=" + url + "]";
	}

	
	

}
