package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Admin;
import com.example.app.domain.User;

@Mapper
public interface AdminMapper {
	
	Admin loginByAdmin(String adminId);
	List<User> selectAllUser();
	User selectUser(int id);
	void deleteUserById(int id);
	void softDeleteUserById(int id);
	void unSoftDeleteUserByuserId(String userId);
	void updateUser(int id, String userId, String loginPass);
	long countRecipes(String userId);
	
}
