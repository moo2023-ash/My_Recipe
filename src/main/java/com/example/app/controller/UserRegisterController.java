package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.User;
import com.example.app.domain.validation.LoginGroup;
import com.example.app.service.RegisterService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserRegisterController {

	private final RegisterService service; 
	
	@GetMapping("/register")
	public String registerHome(Model model) {
		model.addAttribute("user", new User());
		return "registerHome";
	}
	
	@PostMapping("/register")
	public String registerUser(
			@Validated(LoginGroup.class) User user,
				Errors errors,
				HttpSession session) {
		if(errors.hasErrors()) {
			return "registerHome";
		}
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("register" + user);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		service.register(user);
		session.setAttribute("userId", user.getUserId());
		return "redirect:/recipe/home";
	}
}


