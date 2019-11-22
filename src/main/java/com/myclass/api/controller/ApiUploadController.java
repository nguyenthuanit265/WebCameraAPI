package com.myclass.api.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.entity.Photo;
import com.myclass.entity.User;
import com.myclass.repository.PhotoRepository;
import com.myclass.repository.UserRepository;

@RestController
@RequestMapping("/api/file")
public class ApiUploadController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PhotoRepository photoRepository;

	@PostMapping("/upload")
	@CrossOrigin(origins = "*")
	public Object post(@RequestParam(name = "file") MultipartFile file, String email, String password) {

		try {
			System.out.println("Email: " + email);
			System.out.println("Password: " + password);
			// Lấy đường dẫn tuyệt đối của thư mục chứa file upload
			// String folderPath = request.getServletContext().getRealPath("/upload/");
			 String folderPath = System.getProperty("user.dir");
			//String folderPath = "";
			folderPath += "\\src\\main\\resources\\static" + "\\upload\\";
			// Sử dụng đối tượng File của java.io để kiểm tra thư mục
			System.out.println("Folder save image: " + folderPath);
			File dir = new File(folderPath);

			// Nếu chưa tồn tại thư mục upload thì tạo mới
			if (!dir.exists()) {
				dir.mkdir();
			}

			// Lấy ra tên file vừa gửi từ client lên
			String fileName = file.getOriginalFilename();

			// Sử dụng đối tượng File của java.io để lưu file
			File filePath = new File(folderPath + fileName);

			System.out.println("filepath: " + filePath.getAbsolutePath());

			User user = null;
			user = userRepository.findByEmail(email);

			if (user == null) {
				System.out.println("Lỗi null");
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}
			if (user != null) {
				System.out.println("User in database: " + user.toString());
				if (!user.getPassword().equals(password)) {
					return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
				}
			}

			// Sử dụng phương thức transferTo của MultipartFile để lưu file xuống thư mục
			file.transferTo(filePath);
			// int id=photoRepository.findAll().size();
			// System.out.println("Length photo: " + id);
//			int userIdUpload= user.getId();
			Photo photo = new Photo(fileName, filePath.toString(), 1);
			photoRepository.save(photo);

			System.out.println("Đã save file");
			return new ResponseEntity<String>("/upload/" + fileName, HttpStatus.OK);

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
}
