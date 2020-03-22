package com.carefirst.auth.service;

import java.util.Base64;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carefirst.auth.config.AuthConstants;
import com.carefirst.auth.config.AuthConfigProperties;
import com.carefirst.auth.domain.UserDto;
import com.carefirst.auth.entity.User;
import com.carefirst.auth.repository.AuthenticationRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1/auth-service")
@Api(value = "Authentication Management System", description = "Operations pertaining to user in Authentication Management System")
public class AuthenticationService {
	
	@Autowired
	AuthenticationRepository authenticationRepository;
	
	@Autowired
	AuthConfigProperties authConfigProperties;

	@ApiOperation(value = "Get an User by emailAddress")
	@GetMapping("/users/{id}")
	public ResponseEntity<User> findByEmailId(@PathVariable("id") String emailId) {
		return new ResponseEntity<User>(authenticationRepository.findByEmailId(emailId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Register a User")
	@PostMapping("/registration")
	public ResponseEntity<String> createUser(@ApiParam(value = "Store User data into database table", required = true) @Valid @RequestBody UserDto userDto) {
		String errorMsg = "";
		User user = authenticationRepository.findByEmailId(userDto.getEmailId());
		if(user.getEmailId().equals(userDto.getEmailId())) {
			errorMsg = authConfigProperties.getRegistered();
		} else {
			user = new User();
			user.setEmailId(userDto.getEmailId());
			user.setPassword(String.valueOf(Base64.getEncoder().encode(userDto.getPassword().getBytes())));
			User response = authenticationRepository.save(user);
			errorMsg = response !=null ? authConfigProperties.getSuccessmessage() : AuthConstants.REGISTRATION_FAILED;
		}
		return new ResponseEntity<String> (errorMsg, HttpStatus.OK);
	}
	
}
