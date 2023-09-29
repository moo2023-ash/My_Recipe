//package com.example.app.service;
//
//import com.example.app.domain.RecipeRegister;
//
//public class SendMailServiceImpl implements SendMailService {
//
//	@Override
//	public String buildEmailBody(RecipeRegister recipeRegister) {
//		
//		StringBuilder body = new StringBuilder();
//	    body.append("料理のタイトル: ").append(recipeRegister.getTitle()).append("\n");
//	    body.append("材料1: ").append(recipeRegister.getIngredient1()).append("\n");
//	    body.append("分量1: ").append(recipeRegister.getAmount1()).append("\n");
//	    body.append("材料2: ").append(recipeRegister.getIngredient2()).append("\n");
//	    body.append("分量2: ").append(recipeRegister.getAmount2()).append("\n");
////
//	    return body.toString();
//	}
//
//
//
//}