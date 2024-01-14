package com.findall.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findall.entity.User;
import com.findall.exception.ResourceNotFoundException;
import com.findall.payload.UserDto;
import com.findall.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.dtoToUsers(userDto);
		User savedUser = this.userRepository.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateuser(UserDto userDto, Integer id) {
		// TODO Auto-generated method stub
		User user = this.userRepository.findById(id).orElseThrow((()-> new ResourceNotFoundException("User","username",id)));
		user.setFullname(userDto.getFullname());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		User updateUser = this.userRepository.save(user);
		UserDto userDto1 = this.userToDto(updateUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user = this.userRepository.findById(id).orElseThrow((()-> new ResourceNotFoundException("User","id", id)));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = this.userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(String username) {
		User user = this.userRepository.findByUsername(username);
		this.userRepository.delete(user);

	}
	
	private User dtoToUsers(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		
		
//		user.setUsername(userDto.getUsername());
//		user.setFullname(userDto.getFullname());
//		user.setPassword(userDto.getPassword());
//		user.setMobileno(userDto.getMobileno());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
		
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		
//		userDto.setUsername(user.getUsername());
//		userDto.setFullname(user.getFullname());
//		userDto.setPassword(user.getPassword());
//		userDto.setMobileno(user.getMobileno());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
		
		return userDto;
	}

}
