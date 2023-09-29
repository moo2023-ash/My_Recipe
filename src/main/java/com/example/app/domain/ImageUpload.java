package com.example.app.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ImageUpload {

	private int id;
	private  MultipartFile imageUpfile; 
}
