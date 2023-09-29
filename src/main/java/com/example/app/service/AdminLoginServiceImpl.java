package com.example.app.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Admin;
import com.example.app.mapper.AdminMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor

public class AdminLoginServiceImpl implements AdminLoginService {

	private final AdminMapper adminMapper;



	@Override
	public boolean isCorrectIdAndPasswordByAdmin(String adminId, String loginPass) {
		Admin admin = adminMapper.loginByAdmin(adminId);

		if (admin == null) {
			return false;
		}
		if (!BCrypt.checkpw(loginPass, admin.getLoginPass())) {
			return false;
		}
		return true;
	}
}

