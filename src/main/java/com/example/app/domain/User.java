package com.example.app.domain;

import java.sql.Date;

import com.example.app.domain.validation.LoginGroup;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {

    private Integer id;

   
    
    @NotBlank
    @Size(min=1, max = 20, groups = {LoginGroup.class})
    private String userId;
    
    @NotBlank
    @Size(min=1, max = 1000)
    private String loginPass;
    
    private String status;
    private Date createdAt;
   

    public User() {
    }



}
