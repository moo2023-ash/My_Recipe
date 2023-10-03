package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.Admin;
import com.example.app.domain.validation.LoginGroup;
import com.example.app.service.AdminLoginService;
import com.example.app.service.RecipeService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

public class AdminRoginController {

	private final AdminLoginService adminLoginService;
	private final RecipeService recipeService;

	@GetMapping("/loginAdmin")
	public String adminRogin(Model model) {
		model.addAttribute("admin", new Admin());
		return "loginAdmin";
	}

	@PostMapping("/loginAdmin")
	public String adminRogin(
			@Validated(LoginGroup.class) Admin admin,
			Errors errors,
			HttpSession session) {

		if (!adminLoginService.isCorrectIdAndPasswordByAdmin(admin.getAdminId(), admin.getLoginPass())) {
			errors.reject("wrong.id.pass");
			return "loginAdmin";
		}

		session.setAttribute("adminId", admin.getAdminId());
		
		System.out.println("adminId:" + admin.getAdminId());

		return "redirect:/admin/home";
	}
	
	
}
