package com.myclass.controller;

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
		model.addAttribute("photos", photoRepository.findAll());
		return "photo/index";
	}

	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("photo", new Photo());
		model.addAttribute("users", userRepository.findAll());
		return "photo/add";
	}

	@PostMapping("add")
	public String add(Model model, @Valid @ModelAttribute("user") Photo photo, BindingResult errors) {
		// Kiểm tra nhập liệu
		if (errors.hasErrors()) {
			model.addAttribute("users", photoRepository.findAll());
			return "photo/add";
		}

		photoRepository.save(photo);
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/photo";
	}

//	@GetMapping("edit/{id}")
//	public String edit(@PathVariable("id") String id, Model model) {
//		System.out.println("ID BEFORE: " + id);
//		// model.addAttribute("id", id);
//		model.addAttribute("user", userRepository.findById(id));
//		model.addAttribute("roles", photoRepository.findAll());
//		return "user/edit";
//	}
//
//	@PostMapping("edit")
//	public String edit(Model model, @Valid @ModelAttribute("user") User user, BindingResult errors) {
//		// Bắt lỗi nhập liệu
//		if (errors.hasErrors()) {
//			System.out.println("ERROR");
//			model.addAttribute("roles", photoRepository.findAll());
//			return "user/edit";
//		}
//		System.out.println("ID AFTER: " + user.getId());
//		// Cập nhật User
//		userRepository.update(user);
//		// Chuyển hướng về trang danh sách
//		return "redirect:/admin/user";
//	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		// Xóa User theo id
		photoRepository.deleteById(id);
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/photo";
	}
}
