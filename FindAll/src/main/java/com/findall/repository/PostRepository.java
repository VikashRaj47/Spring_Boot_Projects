package com.findall.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.findall.entity.Category;
import com.findall.entity.Post;
import com.findall.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer>{

	Page<Post> findByCategory(Category category, Pageable pageable);
	List<Post> findByUser(User user);
	List<Post> findByTitleContaining(String title);
}
