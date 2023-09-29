package com.example.app.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.app.domain.Recipe;
import com.example.app.domain.RecipeRegister;

public interface RecipeService {

	void andPhotoInsertRecipe(RecipeRegister recipeRegister, MultipartFile imageUpfile);
	
	List<Recipe> selectByIdByService(int id);
	
	List<Recipe> getRecipeListByPage(int page, int numPerPage, String currentUserId)throws Exception;

	int getTotalPages(int numPerPage, String userId) throws Exception;
	
	void updateRecipe(RecipeRegister recipeRegister, MultipartFile imageUpfiled) throws Exception;
}
