package com.findall.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private String username;
	
	@NotEmpty
	@Size(min=4, message="Username must be of 4 characters.") 
	private String fullname;
	@NotNull
	@Size(min=3, max=12, message="Password must be between of 3 to 12 characters.")
	private String password;
	private String mobileno;
	@Email(message="Email address is not valid.")
	private String email;
	private String about;
}
