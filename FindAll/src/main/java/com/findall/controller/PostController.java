package com.findall.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.findall.payload.ApiResponse;
import com.findall.payload.PostDto;
import com.findall.payload.PostResponse;
import com.findall.service.FileService;
import com.findall.service.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;

	//create
	@PostMapping("/category/{categoryId}/user/{userId}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto, @PathVariable Integer categoryId, @PathVariable Integer userId){
		PostDto createPost = this.postService.createPost(postdto, categoryId, userId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}
	
	//get all Post by Category
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<PostResponse> getPostByCategory(@PathVariable Integer categoryId,
			@RequestParam(value="pageNumber", defaultValue="0",required=false) Integer pageNumber, 
			@RequestParam(value="pageSize", defaultValue="5",required=false) Integer pageSize){
		
		PostResponse postResponse = this.postService.getPostByCategory(categoryId, pageNumber, pageSize);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}
	
	//get All Post by User
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		
		List<PostDto> postByUser = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postByUser, HttpStatus.OK);
	}
	
	//get Post by Id
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto postDto = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
	//get All Post
	@GetMapping("/")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber", defaultValue="0",required=false) Integer pageNumber, 
			@RequestParam(value="pageSize", defaultValue="5",required=false) Integer pageSize,
			@RequestParam(value="sortBy", defaultValue="postId",required=false) String sortBy,
			@RequestParam(value="sortDirection", defaultValue="asc",required=false) String sortDirection){
		PostResponse postResponse= this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDirection);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}
	
	//Update post
	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
		PostDto updatedpost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedpost,HttpStatus.OK);
	}
	
	//Delete Post
	@DeleteMapping("/{postId}")
	public ApiResponse deteletPost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("Post deleted successfully.",true);
	}
	
	//Search
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword){
		List<PostDto> postDto = this.postService.searchPostsByTitle(keyword);
		return new ResponseEntity<List<PostDto>>(postDto, HttpStatus.OK);
	}
	
	//Post image upload
	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@PathVariable Integer postId, @RequestParam("image") MultipartFile image ) throws IOException{
		PostDto postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}
	
	//serve post image
	@GetMapping(value="image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void imageDownload(@PathVariable String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
