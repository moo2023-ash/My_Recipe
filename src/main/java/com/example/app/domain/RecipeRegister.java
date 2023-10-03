package com.example.app.domain;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RecipeRegister {

	private int id;
	
	@NotBlank
	@Size(min=1, max = 30)
	private String title;
	
	private String userId;
	private String photo;
	private Date createdAt;
	private int likeCount;
	@Size(max = 15)
	private String ingredient1;
	@Size(max = 15)
	private String ingredient2;
	@Size(max = 15)
	private String ingredient3;
	@Size(max = 15)
	private String ingredient4;
	@Size(max = 15)
	private String ingredient5;
	@Size(max = 15)
	private String ingredient6;
	@Size(max = 15)
	private String ingredient7;
	@Size(max = 15)
	private String ingredient8;
	@Size(max = 15)
	private String ingredient9;
	@Size(max = 15)
	private String ingredient10;
	@Size(max = 10)
	private String amount1;
	@Size(max = 10)
	private String amount2;
	@Size(max = 10)
	private String amount3;
	@Size(max = 10)
	private String amount4;
	@Size(max = 10)
	private String amount5;
	@Size(max = 10)
	private String amount6;
	@Size(max = 10)
	private String amount7;
	@Size(max = 10)
	private String amount8;
	@Size(max = 10)
	private String amount9;
	@Size(max = 10)
	private String amount10;
	
	private String textarea1;
	
	private String textarea2;
	
	private String textarea3;
	
	private String textarea4;
	
	private String textarea5;
	
	private String textarea6;
	
	private String textarea7;
	
	private String textarea8;
	
	private String textarea9;
	
	private String textarea10;
}
