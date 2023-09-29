package com.example.app.domain;

import java.sql.Date;
import java.util.List;

import com.example.app.domain.validation.LoginGroup;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Recipe {
	
	private Integer id;
	
	@NotBlank
	private String title;
	
	@NotBlank(groups={LoginGroup.class})
	private String userId;
	
	private String photo; 
	private Date createdAt;
	private int likeCount;
	private List<Ingredient> ingredients;
	private List<Amount> amounts;
	private List<Textarea> textarea;	
}
