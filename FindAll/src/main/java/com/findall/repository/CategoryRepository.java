package com.findall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.findall.entity.Category;

import jakarta.transaction.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

//	@Modifying
//	@Transactional
//	@Query(value = "update category set title=:categoryTitle, description=:description where category_id=:categoryId", nativeQuery = true)
//	public Category updateCategoryByCustomQuery(@Param("categoryTitle") String categoryTitle, @Param("description") String description, @Param("categoryId") Integer categoryId);
	}
