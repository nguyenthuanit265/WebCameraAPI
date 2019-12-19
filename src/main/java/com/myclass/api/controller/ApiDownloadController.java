//package com.myclass.api.controller;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Base64;
//import java.util.List;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.io.IOUtils;
//import org.apache.tomcat.util.http.fileupload.FileItemIterator;
//import org.apache.tomcat.util.http.fileupload.FileItemStream;
//import org.apache.tomcat.util.http.fileupload.FileUploadException;
//import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.myclass.entity.Photo;
//import com.myclass.entity.User;
//import com.myclass.repository.PhotoRepository;
//import com.myclass.repository.UserRepository;
//import com.myclass.util.MediaTypeUtils;
//
//@RestController
//@RequestMapping("api/file")
//public class ApiDownloadController {
//	@Autowired
//	private UserRepository userRepository;
//	@Autowired
//	private PhotoRepository photoRepository;
//
//	@Autowired
//	private ServletContext servletContext;
//
//	@GetMapping("download")
//	@CrossOrigin(origins = "*")
//	public ResponseEntity<InputStreamResource> download(HttpServletRequest request,
//			@RequestParam(name = "email") String email, @RequestParam(name = "directory") String directory)
//			throws IOException {
//		System.out.println("EMAIL OF USER: " + email);
//
//		System.out.println("Directory of image: " + directory);
//
//		HttpHeaders responseHeader = new HttpHeaders();
//		User user = userRepository.findByEmail(email);
//		System.out.println("USER IN DATABASE: ");
//		System.out.println(user.toString());
//
//		System.out.println("IMAGE IN DATABASE OF USER: ");
//		List<Photo> photos = user.getImages();
//		for (Photo photo : photos) {
//			System.out.println(photo.toString());
//		}
//		File file1= new File(photos.get(0).getUrl());
//		
//		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, file1.getName());
//		System.out.println("fileName: " + file1.getName());
//		System.out.println("mediaType: " + mediaType);
//
//		byte[] file = Base64.getEncoder().encodeToString(file1.getName()).getBytes();
//		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//
//		return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType("application/octet-stream"))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
//                .contentLength(file.length())
//                .body(new ByteArrayResource(file));
//	}
//	
//	private byte[] getBytesFromFile(HttpServletRequest request) throws ImageException {
//	    ServletFileUpload upload = new ServletFileUpload();
//	    byte[] bytes = null;
//	    FileItemIterator iter;
//	    try {
//	        iter = upload.getItemIterator(request);
//	        while(iter.hasNext()) {
//	            FileItemStream item = iter.next();
//	            InputStream stream = item.openStream();
//	            bytes = IOUtils.toByteArray(stream);
//	        }
//	        return bytes;
//	    } catch (IOException | FileUploadException e) {
//	        throw new ImageException("The problem while storing file. Try again.",e);
//	    }
//}
