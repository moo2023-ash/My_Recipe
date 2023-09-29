package com.example.app.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.User;
import com.example.app.mapper.RegisterMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor

public class RegisterServiceImpl implements RegisterService {

	
	private final RegisterMapper registerMapper;

	@Override
	public void register(User user) {
	
		String loginPass = user.getLoginPass();
		String hashedLoginPass = 
		   
		BCrypt.hashpw(loginPass, BCrypt.gensalt());
		
		System.out.println("--------------------------------");
		System.out.println("user" + user);
		System.out.println("--------------------------------");
		
		registerMapper.registerUser(user.getUserId(), hashedLoginPass);
	
		
	}
}
