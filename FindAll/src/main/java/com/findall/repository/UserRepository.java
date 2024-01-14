package com.findall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findall.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUsername(String username);

}
