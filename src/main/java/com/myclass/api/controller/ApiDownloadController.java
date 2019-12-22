package com.myclass.api.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.PhotoDto;
import com.myclass.entity.Photo;
import com.myclass.entity.User;
import com.myclass.repository.PhotoRepository;
import com.myclass.repository.UserRepository;

@RestController
@RequestMapping("api/file")
public class ApiDownloadController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PhotoRepository photoRepository;

	@GetMapping("/download")
	@CrossOrigin(origins = "*")
	public Object download(String email, String password) {

		System.out.println("email download: " + email);
		System.out.println("password download: " + password);
		ArrayList<String> listLinkPhoto = null;
		ArrayList<Photo> listPhoto = null;
		ArrayList<PhotoDto> listPhotoDto = new ArrayList<PhotoDto>();
		try {
			User user = null;
			user = userRepository.findByEmail(email);
			if (user == null) {
				System.out.println("Lỗi null");
				return new ResponseEntity<ArrayList<String>>(HttpStatus.BAD_REQUEST);
			}
			if (user != null) {
				System.out.println("User in database: " + user.toString());
				if (!user.getPassword().equals(password)) {
					return new ResponseEntity<ArrayList<String>>(HttpStatus.BAD_REQUEST);
				}
			}

			int userIdDownload = user.getId();

			listPhotoDto = photoRepository.getFile(userIdDownload);
			
			for (PhotoDto photoDto : listPhotoDto) {
				// listLinkPhoto.add(photo.getUrl());
				// System.out.println(photo.toString());
				
				System.out.println(photoDto.toString());
				
				

			}
			return new ResponseEntity<ArrayList<PhotoDto>>(listPhotoDto, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println("upload:" + e.getMessage());
		}
		return new ResponseEntity<ArrayList<PhotoDto>>(listPhotoDto, HttpStatus.NOT_FOUND);

	}

}
