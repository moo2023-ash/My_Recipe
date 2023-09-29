package com.example.app.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Admin {

    private Integer id;

   
    
    @NotBlank
    @Size(max = 20)
    private String adminId;
    
    @NotBlank
    @Size(max = 1000)
    private String loginPass;

   

    public Admin() {
    }



}
