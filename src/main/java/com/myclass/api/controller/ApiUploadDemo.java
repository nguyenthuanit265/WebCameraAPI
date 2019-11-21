package com.myclass.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
public class ApiUploadDemo {
	@PostMapping(value = "/updatePhoto")
	public String updateUserPhoto(@RequestPart(name = "img") MultipartFile img) {
		{
			System.out.println("Request  update photo " + img.getOriginalFilename());
			return "OK";
		}
	}

}
