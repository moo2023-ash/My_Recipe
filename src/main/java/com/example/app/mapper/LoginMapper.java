package com.example.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.User;

@Mapper
public interface LoginMapper {

	User selectByLoginId(String userId);
	
}
