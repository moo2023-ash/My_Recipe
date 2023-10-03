package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.domain.RecipeRegister;
import com.example.app.mapper.RecipeMapper;
import com.example.app.service.MailService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sendMail")
public class SendMailController {

    private final MailService mailService;
    private final RecipeMapper recipeMapper;

    @GetMapping("/answer")
    public String sendMailForm() {
        return "sendMailForm"; 
    }

    @PostMapping("/answer")
    public String sendMail(@RequestParam("name") String name,
                           @RequestParam("email") String email,
                           @RequestParam("title") String title,
                           @RequestParam("message") String message,
                           Model model) {
    	String titleToSend = "サニーヒルより";
        String messageToSend = name + " 様\n" + "お問い合わせ頂き、ありがとうございます。\n"
                + "以下の内容で承りました。\n"
                + "------------------------\n"
                + "件名: " + title + "\n"
                + "内容:\n" + message + "\n"
                + "------------------------\n"
                + "近日中に弊社担当の者より、お返事致します。";

        try {
            mailService.send(email, titleToSend, messageToSend);
            model.addAttribute("msg", "お問い合わせ頂き、ありがとうございました。");
        } catch (Exception e) {
            model.addAttribute("msg", "申し訳ございません。送信に失敗しました。");
        }
        return "sendMailForm"; 
    }
    
    @GetMapping("/ingredient/{id}")
    public String sendIngredientMailForm() {
        return "sendIngredientMailForm"; 
    }
    
    @PostMapping("/ingredient/{id}")
    public String sendMail(
    		@RequestParam("email") String email,
    		@PathVariable("id") int id,
    		Model model
    		) {
    	RecipeRegister recipeRegister = recipeMapper.selectRegisterById(id);
    	String emailBody = mailService.buildEmailBody(recipeRegister);		
    	String title = "材料一覧";
    	String message = emailBody;
    
    	try {
    		mailService.sendIngredient(email, title, message);
            model.addAttribute("msg", "材料メール送信完了しました。");
        } catch (Exception e) {
            model.addAttribute("msg", "申し訳ございません。送信に失敗しました。");
        }
        return "sendIngredientMailForm"; 
    }
    
    @GetMapping("/ingredient2/{id}")
    public String sendIngredientMailForm2() {
    	return "/admin/sendIngredientMailForm"; 
    }
    
    @PostMapping("/ingredient2/{id}")
    public String sendMail2(
    		@RequestParam("email") String email,
    		@PathVariable("id") int id,
    		Model model
    		) {
    	RecipeRegister recipeRegister = recipeMapper.selectRegisterById(id);
    	String emailBody = mailService.buildEmailBody(recipeRegister);		
    	String title = "材料一覧";
    	String message = emailBody;
    	
    	try {
    		mailService.sendIngredient(email, title, message);
    		model.addAttribute("msg", "材料メール送信完了しました。");
    	} catch (Exception e) {
    		model.addAttribute("msg", "申し訳ございません。送信に失敗しました。");
    	}
    	return "/admin/sendIngredientMailForm"; 
    }
}
