package com.findall.service;

import java.util.List;

import com.findall.payload.UserDto;


public interface UserService {

	UserDto createUser(UserDto user);
	UserDto updateuser(UserDto user, Integer id);
	UserDto getUserById(Integer id);
	List<UserDto> getAllUsers();
	void deleteUser(String username);
}
