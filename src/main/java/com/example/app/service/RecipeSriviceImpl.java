package com.example.app.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.domain.Recipe;
import com.example.app.domain.RecipeRegister;
import com.example.app.mapper.RecipeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeSriviceImpl implements RecipeService {

	private final RecipeMapper recipeMapper;

	@Override
	public void andPhotoInsertRecipe(RecipeRegister recipeRegister, MultipartFile imageUpfile) {
		
		if (!imageUpfile.isEmpty()) {
			String photo = imageUpfile.getOriginalFilename();
			recipeRegister.setPhoto(photo);
			//		 File dest = new File("file:///Users/mukawachirei/Pictures/uploads/" + photo);
			File dest = new File("/home/trainee/uploads/" + photo);
			try {
				imageUpfile.transferTo(dest);
			} catch (IllegalStateException | IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		recipeMapper.insertRecipe(recipeRegister);
	}

	@Override
	public List<Recipe> selectByIdByService(int id) {
		return recipeMapper.selectById(id);
	}

	@Override
	public List<Recipe> getRecipeListByPage(int page, int numPerPage, String currentUserId) throws Exception {
		int offset = numPerPage * (page - 1);
		return recipeMapper.selectLimited(offset, numPerPage, currentUserId);
	}

	@Override
	public int getTotalPages(int numPerPage, String userId) throws Exception {
		double totalNum = (double) recipeMapper.count(userId);
		return (int) Math.ceil(totalNum / numPerPage);
	}

	@Override
	public void updateRecipe(RecipeRegister recipeRegister, MultipartFile imageUpfile)
			throws Exception {

		System.out.println(recipeRegister);
		if (!imageUpfile.isEmpty()) {
			String photo = imageUpfile.getOriginalFilename();
			recipeRegister.setPhoto(photo);
			//		 File dest = new File("file:///Users/mukawachirei/Pictures/uploads/" + photo);
//			File dest = new File("C:/Users/zd2N09/uploads/" + photo);
			File dest = new File("/home/trainee/uploads/" + photo);
			try {
				imageUpfile.transferTo(dest);
			} catch (IllegalStateException | IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		recipeMapper.update(recipeRegister);
	}
}
