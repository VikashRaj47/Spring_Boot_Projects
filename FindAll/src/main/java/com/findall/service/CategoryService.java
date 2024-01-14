package com.findall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.findall.payload.CategoryDto;

@Service
public interface CategoryService {

	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//get
	CategoryDto getCategoryById(Integer categoryId);
	
	//getAll
	List<CategoryDto> getAllCategory();
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
//	update--test
//	CategoryDto updateCategoryByCustomQuery(String categoryTitle, String description, Integer categoryId);
	
	//delete
	void deleteCategory(Integer categoryId);
}
