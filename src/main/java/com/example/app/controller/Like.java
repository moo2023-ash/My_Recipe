//package com.example.app.controller;
//
//import java.util.List;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import com.example.app.domain.Recipe;
//import com.example.app.domain.RecipeRegister;
//import com.example.app.mapper.RecipeMapper;
//import com.example.app.service.RecipeService;
//
//import lombok.RequiredArgsConstructor;
//
//@Controller
//@RequiredArgsConstructor
//public class Like {
//	
//	private final RecipeMapper recipeMapper;
//	private final RecipeService recipeService;
//
//	@GetMapping("/like/{id}")
//	private String like(
//			@PathVariable("id") int id,
//			Model model) throws Exception {
//		
//		RecipeRegister likeCounts = recipeMapper.selectRegisterById(id);
//		
//		int currentLikeCount = likeCounts.getLikeCount();
//		int newLikeCount = currentLikeCount + 1;
//		likeCounts.setLikeCount(newLikeCount);
//		recipeMapper.update(likeCounts);
//		model.addAttribute("likeCounts", likeCounts);
//		
//		
//		
//		List<Recipe> recipesDetail = recipeService.selectByIdByService(id);
//		model.addAttribute("recipesDetail", recipesDetail);
//		
//		
//		return "recipe/detailRogin2";
//	}
//	
//	private String like(
//			@PathVariable("id") int id,
//			Model model) throws Exception {
//		
//		List<Recipe> recipesDetail = recipeService.selectByIdByService(id);;
//		
//		int currentLikeCount = recipesDetail.getLikeCount();
//		int newLikeCount = currentLikeCount + 1;
//		recipesDetail.setLikeCount(newLikeCount);
//		recipeMapper.update(recipesDetail);
//		
//		model.addAttribute("recipesDetail", recipesDetail);
//		
//		
//		return "recipe/detailRogin";
//	}
//}
