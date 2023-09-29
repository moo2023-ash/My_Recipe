package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.app.domain.Recipe;
import com.example.app.domain.RecipeRegister;

@Mapper
public interface RecipeMapper {

	void insertRecipe(RecipeRegister recipeRegister);


	List<RecipeRegister> selectAll();
	
	List<RecipeRegister> selectByUserId(String userId);
	
	List<Recipe> selectById(int id);
	
	RecipeRegister selectRegisterById(int id);
	
	Long count(String userId) throws Exception;
	
	List<Recipe> selectLimited(
			@Param("offset") int offset,
			@Param("limit") int limit, 
			@Param("currentUserId") String currentUserId) throws Exception;
	
	void delete(int id) throws Exception;
	void deleteRecipeBytitle(String title) throws Exception;
	
	void update(RecipeRegister recipeRegister) throws Exception;
	
	RecipeRegister searchRecipe(String keyword);
}
