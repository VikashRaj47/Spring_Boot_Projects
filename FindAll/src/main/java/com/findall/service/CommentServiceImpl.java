package com.findall.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findall.entity.Comment;
import com.findall.entity.Post;
import com.findall.exception.ResourceNotFoundException;
import com.findall.payload.CommentDto;
import com.findall.repository.CommentRepository;
import com.findall.repository.PostRepository;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment save = this.commentRepository.save(comment);
		return this.modelMapper.map(save, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer CommentId) {

		Comment comment = this.commentRepository.findById(CommentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "commentId", CommentId));
		this.commentRepository.delete(comment);
	}

}
