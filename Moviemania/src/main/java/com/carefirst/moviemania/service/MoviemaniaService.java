package com.carefirst.moviemania.service;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carefirst.moviemania.repository.MoviemaniaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1/moviemania")
@Api(value = "Moviemania Management System", description = "Operations pertaining to user in Moviemania Management System")
public class MoviemaniaService {

	@Autowired
	MoviemaniaRepository moviemaniaRepository;
	
	@ApiOperation(value = "Register a User")
	@PostMapping("/users")
	public ResponseEntity<String> createUser(@ApiParam(value = "User object store in database table", required = true) @Valid @RequestBody Map<String, String> usersData) throws Exception {
		return new ResponseEntity<String>(moviemaniaRepository.createUser(usersData), HttpStatus.OK);
	}
	
}
