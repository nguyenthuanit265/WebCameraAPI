package com.myclass.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.entity.Photo;
import com.myclass.repository.PhotoRepository;
import com.myclass.repository.UserRepository;

@Controller
@RequestMapping("admin/photo")
public class PhotoController {
	@Autowired
	PhotoRepository photoRepository;

	@Autowired
	UserRepository userRepository;

	@GetMapping("")
	public String index(Model model) {
		List<Photo> photos= photoRepository.findAll();
		for (Photo photo : photos) {
			
			photo.setUrl("/assets/upload/" + photo.getNamePhoto());
			System.out.println(photo.toString());
		}
		model.addAttribute("photos", photos);
		//model.addAttribute(attributeName, attributeValue)
		return "photo/index";
	}

	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("photo", new Photo());
		model.addAttribute("users", userRepository.findAll());
		return "photo/add";
	}

	@PostMapping("add")
	public String add(Model model, @Valid @ModelAttribute("photo") Photo photo, BindingResult errors) {
		// Kiểm tra nhập liệu
		if (errors.hasErrors()) {
			model.addAttribute("users", photoRepository.findAll());
			return "photo/add";
		}
		//photo.setId(photoRepository.findAll().size()+1);
		
		//System.out.println();
		photoRepository.save(photo);
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/photo";
	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		System.out.println("ID BEFORE: " + id);
		// model.addAttribute("id", id);
		Optional<Photo> photoEdit = photoRepository.findById(id);
		model.addAttribute("photo", photoRepository.findById(id));
		System.out.println(photoEdit.toString());
		model.addAttribute("users", userRepository.findAll());
		return "photo/edit";
	}

	@PostMapping("edit")
	public String edit(Model model, @Valid @ModelAttribute("photo") Photo photo, BindingResult errors) {
		// Bắt lỗi nhập liệu
		if (errors.hasErrors()) {
			System.out.println("ERROR");
			model.addAttribute("users", userRepository.findAll());
			return "photo/edit";
		}
		System.out.println("ID AFTER: " + photo.getId());
		// Cập nhật User
		photoRepository.saveAndFlush(photo);
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/photo";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		// Xóa Photo theo id
		Optional<Photo> photo = photoRepository.findById(id);
		System.out.println("Photo delete: ");
		System.out.println(photo.toString());
//		File file=new File(photo.get().getUrl());
//		if(file.exists()){
//			if(file.delete()){
//				System.out.println("File deleted successfully");
//			}else{
//				System.out.println("Fail to delete file");
//			}
//		}
		photoRepository.deleteById(id);
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/photo";
	}
}
