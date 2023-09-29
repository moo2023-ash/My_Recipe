package com.example.app.domain;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {

    private Integer id;

   
    
    @NotBlank
    @Size(max = 20)
    private String userId;
    
    @NotBlank
    @Size(max = 1000)
    private String loginPass;
    
    private String status;
    private Date createdAt;
   

    public User() {
    }



}
