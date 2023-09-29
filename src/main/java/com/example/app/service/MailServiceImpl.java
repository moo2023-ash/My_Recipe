package com.example.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.app.domain.RecipeRegister;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
     private String senderEmail;

    @Override
    public void send(String email, String titleToSend, String messageToSend) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject(titleToSend);
        mail.setText(messageToSend);
        mail.setFrom(senderEmail);
       
        mail.setBcc("nemutaizo1020@yahoo.co.jp"); 
        javaMailSender.send(mail);
    }
    
    @Override
    public void sendIngredient(String toEmail, String title, String message) {
    	SimpleMailMessage mail = new SimpleMailMessage();
    	mail.setTo(toEmail);    	
    	mail.setSubject(title);
    	mail.setText(message);
    	mail.setFrom(senderEmail);
    	
    	mail.setBcc("nemutaizo1020@yahoo.co.jp"); 
    	javaMailSender.send(mail);
    }

    @Override
	public String buildEmailBody(RecipeRegister recipeRegister) {
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("料理のタイトル: ").append(recipeRegister.getTitle()).append("\n");
		stringBuilder.append("材料1: ").append(recipeRegister.getIngredient1()).append("\n");
		stringBuilder.append("分量1: ").append(recipeRegister.getAmount1()).append("\n");
		stringBuilder.append("材料2: ").append(recipeRegister.getIngredient2()).append("\n");
		stringBuilder.append("分量2: ").append(recipeRegister.getAmount2()).append("\n");
		stringBuilder.append("材料3: ").append(recipeRegister.getIngredient3()).append("\n");
		stringBuilder.append("分量3: ").append(recipeRegister.getAmount3()).append("\n");
		stringBuilder.append("材料4: ").append(recipeRegister.getIngredient4()).append("\n");
		stringBuilder.append("分量4: ").append(recipeRegister.getAmount4()).append("\n");
		stringBuilder.append("材料5: ").append(recipeRegister.getIngredient5()).append("\n");
		stringBuilder.append("分量5: ").append(recipeRegister.getAmount5()).append("\n");
		stringBuilder.append("材料6: ").append(recipeRegister.getIngredient6()).append("\n");
		stringBuilder.append("分量6: ").append(recipeRegister.getAmount6()).append("\n");
		stringBuilder.append("材料7: ").append(recipeRegister.getIngredient7()).append("\n");
		stringBuilder.append("分量7: ").append(recipeRegister.getAmount7()).append("\n");
		stringBuilder.append("材料8: ").append(recipeRegister.getIngredient8()).append("\n");
		stringBuilder.append("分量8: ").append(recipeRegister.getAmount8()).append("\n");
		stringBuilder.append("材料9: ").append(recipeRegister.getIngredient9()).append("\n");
		stringBuilder.append("分量9: ").append(recipeRegister.getAmount9()).append("\n");
		stringBuilder.append("材料10: ").append(recipeRegister.getIngredient10()).append("\n");
		stringBuilder.append("分量10: ").append(recipeRegister.getAmount10()).append("\n");

	    return stringBuilder.toString();
    }
}
