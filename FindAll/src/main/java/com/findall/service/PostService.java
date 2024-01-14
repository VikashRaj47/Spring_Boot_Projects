package com.findall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.findall.entity.Post;
import com.findall.payload.PostDto;
import com.findall.payload.PostResponse;

@Service
public interface PostService {

	//create
	PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);
	
	//Update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//Delete
	void deletePost(Integer postId);
	
	//get Single Post
	PostDto getPostById(Integer postId);
	
	//get all posts
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
	
	//get all posts by Category
	PostResponse getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize);
	
	//get all posts by User
	List<PostDto> getPostByUser(Integer userId);
	
	//Search Post
	List<PostDto> searchPostsByTitle(String keyword);
}	
