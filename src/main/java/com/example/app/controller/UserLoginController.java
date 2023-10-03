package com.example.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.domain.Recipe;
import com.example.app.domain.RecipeRegister;
import com.example.app.domain.User;
import com.example.app.domain.validation.LoginGroup;
import com.example.app.mapper.RecipeMapper;
import com.example.app.service.LoginService;
import com.example.app.service.RecipeService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserLoginController {

	private final LoginService service;
	private final RecipeMapper recipeMapper;
	private final RecipeService recipeService;

	
	
	
	@GetMapping
	public String showHome(
			@RequestParam(name = "page", defaultValue = "1") Integer page,
			Model model) throws Exception {
		
//		private static final int ADMIN_NUM_PER_PAGE = 10;
		
		model.addAttribute("user", new User());
		
		model.addAttribute("selectAllrecipes", recipeMapper.selectAll());
		model.addAttribute("admins", recipeService.getRecipeListByPage(page,10, "admin"));
		model.addAttribute("page", page);
		model.addAttribute("totalPages", recipeService.getTotalPages(10, "admin"));
		
		return "loginHome";
	}
	
	@PostMapping
	public String login(
			@Validated(LoginGroup.class) User user,
			Errors errors,
			Model model,
			HttpSession session) {
		if (errors.hasErrors()) {
			System.out.println(1111);
			return "loginHome";
		}
		if (!service.isCorrectIdAndPassword(user.getUserId(), user.getLoginPass())) {
			errors.reject("wrong.id.pass");

			return "loginHome";
		}
		
		session.setAttribute("userId", user.getUserId());

		return "redirect:/recipe/home";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/detailRogin/{id}")
	public String showDetail(
			@PathVariable("id") int id, 
			Model model, 
			HttpSession session) {
		
		RecipeRegister recipeRegister = recipeMapper.selectRegisterById(id);
		model.addAttribute("image", recipeRegister);
		List<Recipe> recipesDetail = recipeService.selectByIdByService(id);
		model.addAttribute("recipesDetail", recipesDetail);
		return "/recipe/detailRogin";
	}
	
	
	
	@GetMapping("/detailRogin2/{id}")
	public String showDetail2(
			@PathVariable("id") int id, 
			Model model, 
			HttpSession session) throws Exception {
		
		RecipeRegister recipeRegister = recipeMapper.selectRegisterById(id);
//		int currentLikeCount = recipeRegister.getLikeCount();
//		int newLikeCount = currentLikeCount + 1;
//		recipeRegister.setLikeCount(newLikeCount);
//		recipeMapper.update(recipeRegister);
		model.addAttribute("recipeRegister", recipeRegister);
		
		List<Recipe> recipesDetail = recipeService.selectByIdByService(id);
		model.addAttribute("recipesDetail", recipesDetail);
		return "/recipe/detailRogin2";
	}
	
	@GetMapping("/like/{id}")
	public String likeCount(
			@PathVariable("id") int id, 
			Model model, 
			HttpSession session) throws Exception {
		
		RecipeRegister recipeRegister = recipeMapper.selectRegisterById(id);
		int currentLikeCount = recipeRegister.getLikeCount();
		int newLikeCount = currentLikeCount + 1;
		recipeRegister.setLikeCount(newLikeCount);
		recipeMapper.update(recipeRegister);
		model.addAttribute("recipeRegister", recipeRegister);
		
		List<Recipe> recipesDetail = recipeService.selectByIdByService(id);
		model.addAttribute("recipesDetail", recipesDetail);
//		return "redirect:/recipe/home";
		return "/recipe/detailRogin2";
	}
	
	@GetMapping("/likeAdmin/{id}")
	public String likeCountAdmin(
			@PathVariable("id") int id, 
			Model model, 
			HttpSession session) throws Exception {
		
		RecipeRegister recipeRegister = recipeMapper.selectRegisterById(id);
		int currentLikeCount = recipeRegister.getLikeCount();
		int newLikeCount = currentLikeCount + 1;
		recipeRegister.setLikeCount(newLikeCount);
		recipeMapper.update(recipeRegister);
		model.addAttribute("recipeRegister", recipeRegister);
		
		List<Recipe> recipesDetail = recipeService.selectByIdByService(id);
		model.addAttribute("recipesDetail", recipesDetail);
//		return "redirect:/recipe/home";
		return "/admin/detail";
	}
	


}
