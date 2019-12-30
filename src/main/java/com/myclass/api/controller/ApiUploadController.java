package com.myclass.api.controller;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import com.cloudinary.utils.ObjectUtils;
import com.myclass.config.CloudinaryConfig;
import com.myclass.dto.PhotoDto;
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
	
	private static int count = 1;

	@PostMapping("/upload")
	@CrossOrigin(origins = "*")
	public Object post(@RequestParam(name = "file") MultipartFile file, String email, String password) {
//		try {
//			System.out.println("Email: " + email);
//			System.out.println("Password: " + password);
//			// Lấy đường dẫn tuyệt đối của thư mục chứa file upload
//			// String folderPath = request.getServletContext().getRealPath("/upload/");
//			//String folderPath = System.getProperty("user.dir");
//			// String folderPath = "";
//			String folderPath = "/src/main/resources/static/assets" + "/upload/";
//			// Sử dụng đối tượng File của java.io để kiểm tra thư mục
//			System.out.println("Folder save image: " + folderPath);
//			File dir = new File(folderPath);
//			// Nếu chưa tồn tại thư mục upload thì tạo mới
//			if (!dir.exists()) {
//				dir.mkdir();
//			}
//			// Lấy ra tên file vừa gửi từ client lên
//			String fileName = file.getOriginalFilename();
//			// Sử dụng đối tượng File của java.io để lưu file
//			File filePath = new File(folderPath + fileName);
//			System.out.println("filepath: " + filePath.getAbsolutePath());
//			User user = null;
//			user = userRepository.findByEmail(email);
//			if (user == null) {
//				System.out.println("Lỗi null");
//				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//			}
//			if (user != null) {
//				System.out.println("User in database: " + user.toString());
//				if (!user.getPassword().equals(password)) {
//					return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//				}
//			}
//			// Sử dụng phương thức transferTo của MultipartFile để lưu file xuống thư mục
//			file.transferTo(filePath);
//			// int id=photoRepository.findAll().size();
//			// System.out.println("Length photo: " + id);
//			int userIdUpload = user.getId();
//			Photo photo = new Photo(fileName, filePath.toString(), userIdUpload);
//			photoRepository.save(photo);
//			System.out.println("Đã save file");
//			return new ResponseEntity<String>("/upload/" + fileName, HttpStatus.OK);
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		
		String cloudinaryImgURL=null;
	    try {
	    	
	        File fileDir = new File("rowFiles");
	        if (! fileDir.exists()){
	            fileDir.mkdir();
	        }
	        String fileNameWithExt =file.getOriginalFilename();
	        String fileName=FilenameUtils.removeExtension(file.getOriginalFilename());
	        PhotoDto photoDto = photoRepository.findFileByName(fileName);
	        if (photoDto!=null) {
	        	
	        	fileName+= "_"+ count++;
	        }
	        
	        System.out.println("name file: " + fileName);
	        File physicalFile=new File(file.getOriginalFilename());
	        FileOutputStream fout=new FileOutputStream(fileDir.getName()+"/"+physicalFile);
	        fout.write(file.getBytes());
	        fout.close();
	        File toUpload = new File("rowFiles/"+fileNameWithExt);
	        System.out.println("to Upload: " + toUpload.toString());
	        CloudinaryConfig cloudinary = new CloudinaryConfig("764911318416866","sLVwlxrqxuK-ktLyO2BLwVW0WR8","dy5yspoxj");
	       // System.out.println("API Key:"+cloudinary.config.apiKey);
	        Map params = ObjectUtils.asMap("public_id", "SRWRestImageBase/"+fileName);
//	        Map uploadResult = cloudinary.uploader().upload(toUpload, params);
	        Map uploadResult = cloudinary.upload(toUpload, params);
	        toUpload.delete();
	        System.out.println("==============>>"+uploadResult.get("url"));
	        cloudinaryImgURL=uploadResult.get("url").toString();
	        
	        
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
			
			int userIdUpload = user.getId();
			Photo photo = new Photo(fileName, cloudinaryImgURL, userIdUpload);
			photoRepository.save(photo);
	    } catch (Exception e) {
	        System.out.println("upload:"+e.getMessage());
	    }
	    return new ResponseEntity<Object>("File uploaded successfully:"+cloudinaryImgURL,HttpStatus.OK);
	}
}
