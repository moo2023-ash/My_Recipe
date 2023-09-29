package com.example.app.service;

import org.springframework.stereotype.Service;

import com.example.app.domain.RecipeRegister;

@Service
public interface MailService {
   

    public void send(String email, String titleToSend, String messageToSend);
    
    public void sendIngredient(String toEmail, String title, String message);

    public String buildEmailBody(RecipeRegister recipeRegister);

}
