package com.findall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findall.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
