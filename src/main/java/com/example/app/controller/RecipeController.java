package com.example.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.Recipe;
import com.example.app.domain.RecipeRegister;
import com.example.app.mapper.RecipeMapper;
import com.example.app.service.RecipeService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {

	private static final int NUM_PER_PAGE = 10;

	private final RecipeService recipeService;
	private final RecipeMapper recipeMapper;

	@GetMapping("/home")
	public String showList(
			@RequestParam(name = "page", defaultValue = "1") Integer page,
			Model model,
			HttpSession session) throws Exception {
		String currentUserId = (String) session.getAttribute("userId");
		List<Recipe> selectedRecipes = recipeService.getRecipeListByPage(page, NUM_PER_PAGE, currentUserId);
		List<RecipeRegister> selectAllrecipes = recipeMapper.selectAll();
		
		model.addAttribute("recipes", selectedRecipes);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", recipeService.getTotalPages(NUM_PER_PAGE, currentUserId));
		model.addAttribute("selectAllrecipes", selectAllrecipes);
		return "recipe/home";
	}
	
	@GetMapping("/detail/{id}")
	public String showDetail(
			@PathVariable("id") int id, 
			Model model, 
			HttpSession session) {
		
		RecipeRegister recipeRegister = recipeMapper.selectRegisterById(id);
		model.addAttribute("image", recipeRegister);
		List<Recipe> recipesDetail = recipeService.selectByIdByService(id);
		model.addAttribute("recipesDetail", recipesDetail);
		return "recipe/detail";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteRecipe(
			@PathVariable("id") int id,
			HttpSession session) throws Exception {
		recipeMapper.delete(id);
		return "redirect:/recipe/home";
	}
	
	@GetMapping("/register")
	public String showRecipeHome(Model model) {
		RecipeRegister recipeRegister = new RecipeRegister();
		
		model.addAttribute("recipeRegister", recipeRegister);
		return "register";
	}

	@PostMapping("/register")
	public String add(
			@Valid RecipeRegister recipeRegister,
			Errors errors,
			MultipartFile imageUpfile,
			Model model,
			RedirectAttributes rs,
			HttpSession session) {

//		if (recipeRegister == null) {
//			return "recipe/register";
//		}
//		if (!imageUpfile.isEmpty()) {
//			String type = imageUpfile.getContentType();
//			if (!type.startsWith("image/")) {
//				errors.rejectValue("upfile", "error.not_image_file");
//			}
//		}

		if (errors.hasErrors()) {
			return "register";
		}

		recipeRegister.setUserId((String) session.getAttribute("userId"));
		recipeService.andPhotoInsertRecipe(recipeRegister, imageUpfile);
		rs.addFlashAttribute("statusMessage", "newレシピを登録しました。");
		return "redirect:/recipe/home";
	}
	
	@GetMapping("/update/{id}")
	public String showUpdate(
			@PathVariable("id") int id,
			Model model) {
		RecipeRegister recipeRegister = recipeMapper.selectRegisterById(id);
		model.addAttribute("recipeRegister", recipeRegister);
		return "recipe/update";
	}
	
	@PostMapping("/update/{id}")
	public String updateRecipe(
			@PathVariable("id") int id,
			@Valid RecipeRegister recipeRegister,
			Errors errors,
			MultipartFile imageUpfile,
			Model model,
			RedirectAttributes rs,
			HttpSession session) throws Exception {
		
		if (recipeRegister == null) {
			return "recipe/update";
		}
		if (!imageUpfile.isEmpty()) {
			String type = imageUpfile.getContentType();
			if (!type.startsWith("image/")) {
				errors.rejectValue("upfile", "error.not_image_file");
			}
		}

		if (errors.hasErrors()) {
			return "recipe/update";
		}

		recipeRegister.setUserId(((String) session.getAttribute("userId")));
		recipeRegister.setId(id);
		recipeService.updateRecipe(recipeRegister, imageUpfile);
		rs.addFlashAttribute("statusMessage", "レシピを更新しました。");
		return "redirect:/recipe/home";
	}
	
	@GetMapping("/detailRogin2/{id}")
	public String showDetail2(
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
		return "/recipe/detailRogin2";
	}
}
