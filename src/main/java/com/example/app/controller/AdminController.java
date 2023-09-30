package com.example.app.controller;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.Admin;
import com.example.app.domain.Recipe;
import com.example.app.domain.RecipeRegister;
import com.example.app.domain.User;
import com.example.app.domain.validation.LoginGroup;
import com.example.app.mapper.AdminMapper;
import com.example.app.mapper.LoginMapper;
import com.example.app.mapper.RecipeMapper;
import com.example.app.mapper.RegisterMapper;
import com.example.app.service.AdminLoginService;
import com.example.app.service.RecipeService;
import com.example.app.service.RegisterService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

	private final AdminLoginService adminLoginService;
	private final RecipeService recipeService;
	private final AdminMapper adminMapper;
	private final RecipeMapper recipeMapper;
	private final RegisterService registerService;
	private final LoginMapper loginMapper;
	private final RegisterMapper registerMapper;

	
	
	@GetMapping("/home")
	public String showHome(Model model) {
		model.addAttribute("admin", new Admin());
		
		model.addAttribute("selectAllUser", adminMapper.selectAllUser());
		return "admin/home";
	}
	
	@GetMapping("/recipeTitleList")
	public String showRecipeTitleList (Model model) {
		model.addAttribute("recipeTitleList", recipeMapper.selectAll());
		return "admin/home";
	}
	
//	@GetMapping("/deleteUser/{id}")
//	public String deleteUser (
//			@PathVariable("id") int id) {
//		adminMapper.deleteUserById(id);
//		return "redirect:/admin/home";
//	}
	
	@GetMapping("/softDeleteUserById/{id}")
	public String deleteUser (
			@PathVariable("id") int id) {
		adminMapper.softDeleteUserById(id);
		return "redirect:/admin/home";
	}
	
	@GetMapping("/unSoftDeleteUserByuserId")
	public String unSoftDeleteUserByuserId (
			@RequestParam("userId") String userId) throws Exception {
		adminMapper.unSoftDeleteUserByuserId(userId);
		return "redirect:/admin/home";
	}

	
	@GetMapping("/selectByUserId/{userId}")
	public String selectByUserId(
			@PathVariable("userId") String userId,
			Model model) {
		model.addAttribute("selectByUserId", recipeMapper.selectByUserId(userId));
		return "admin/recipeList";
	}
	
//	@GetMapping("/deleteRecipeBytitle/{title}")
//	public String deleteUserRecipe (
//			@PathVariable("title") String title) throws Exception {
//		recipeMapper.deleteRecipeBytitle(title);
//		return "redirect:/admin/home";
//	}
//	
	
	@GetMapping("/showDetailAdmin/{id}")
	public String showDetailAdmin(
			@PathVariable("id") int id, 
			Model model, 
			HttpSession session) throws Exception {
		
		RecipeRegister recipeRegister = recipeMapper.selectRegisterById(id);
		int currentLikeCount = recipeRegister.getLikeCount();
		int newLikeCount = currentLikeCount + 1;
		recipeRegister.setLikeCount(newLikeCount);
		recipeMapper.update(recipeRegister);
		model.addAttribute("recipeRegister", recipeRegister);

		
	
		model.addAttribute("image", recipeRegister);
		List<Recipe> recipesDetail = recipeService.selectByIdByService(id);
		model.addAttribute("recipesDetail", recipesDetail);

		return "admin/detail";
	}
	
	
	
	
	
	
	@GetMapping("/register")
	public String showRecipeHome(Model model) {
		RecipeRegister recipeRegister = new RecipeRegister();
		
		model.addAttribute("recipeRegister", recipeRegister);
		
		return "admin/register";
	}

	@PostMapping("/register")
	public String add(
			@Valid RecipeRegister recipeRegister,
			MultipartFile imageUpfile,
			Model model,
			Errors errors,
			RedirectAttributes rs,
			HttpSession session) {
		
		System.out.println("recipeRegister : "+ recipeRegister);

		if (recipeRegister == null) {
			return "admin/register";
		}
		if (!imageUpfile.isEmpty()) {
			String type = imageUpfile.getContentType();
			if (!type.startsWith("image/")) {
				errors.rejectValue("upfile", "error.not_image_file");
			}
		}
		if (errors.hasErrors()) {
			return "admin/register";
		}

		recipeRegister.setUserId((String) session.getAttribute("userId"));
		recipeService.andPhotoInsertRecipe(recipeRegister, imageUpfile);
		rs.addFlashAttribute("statusMessage", "newレシピを登録しました。");
		return "redirect:/admin/home";
	
	}
	
	@GetMapping("/update/{id}")
	public String showUpdate(
			@PathVariable("id") int id,
			Model model) {
		RecipeRegister recipeRegister = recipeMapper.selectRegisterById(id);
		model.addAttribute("recipeRegister", recipeRegister);
		return "admin/update";
	}
	
	@PostMapping("/update/{id}")
	public String updateRecipe(
			@PathVariable("id") int id,
			@Valid RecipeRegister recipeRegister,
			MultipartFile imageUpfile,
			Model model,
			Errors errors,
			RedirectAttributes rs,
			HttpSession session) throws Exception {
		
		if (recipeRegister == null) {
			return "admin/update";
		}
		if (!imageUpfile.isEmpty()) {
			String type = imageUpfile.getContentType();
			if (!type.startsWith("image/")) {
				errors.rejectValue("upfile", "error.not_image_file");
			}
		}

		if (errors.hasErrors()) {
			return "admin/update";
		}

		recipeRegister.setUserId(((String) session.getAttribute("userId")));
		recipeRegister.setId(id);
		recipeService.updateRecipe(recipeRegister, imageUpfile);
		rs.addFlashAttribute("statusMessage", "レシピを更新しました。");
		return "redirect:/admin/home";
	}
	
	@GetMapping("/updateUser/{id}")
	public String registerHome(
			@PathVariable("id") int id,
			Model model) {
		
		User user = adminMapper.selectUser(id);
		
	
		model.addAttribute("user", user);
		System.out.println();
		return "admin/registerHome";
	}
	
	@PostMapping("/updateUser/{id}")
	public String registerUser(
			@PathVariable("id") int id,
			@Validated(LoginGroup.class) User user,
				Errors errors,
				HttpSession session) {
		if(errors.hasErrors()) {
			return "admin/registerHome";
		}
		
		String loginPass = user.getLoginPass();
		String hashedLoginPass = 
		    BCrypt.hashpw(loginPass, BCrypt.gensalt());
		System.out.println(hashedLoginPass);
		
		adminMapper.updateUser(id, user.getUserId(), hashedLoginPass);
		
		
		return "redirect:/admin/home";
	}
}
