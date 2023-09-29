package com.example.app.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.User;
import com.example.app.mapper.LoginMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor

public class LoginServiceImpl implements LoginService {

	
	private final LoginMapper loginMapper;

	@Override
	public boolean isCorrectIdAndPassword(String userId, String loginPass) {

		User user = loginMapper.selectByLoginId(userId);
		
		if(user == null) {
			return false;
		}
		if(!BCrypt.checkpw(loginPass, user.getLoginPass())) {
			return false;
		}
		return true;
	}
}
