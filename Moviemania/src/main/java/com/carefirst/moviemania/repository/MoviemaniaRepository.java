package com.carefirst.moviemania.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.carefirst.email.service.EmailService;

import reactor.core.publisher.Mono;

@Repository
public class MoviemaniaRepository {
	@Value("${auth.endpoint}")
	private String authEndpoint;
	
//	@Autowired
//	EmailService emailService;

	public String createUser( Map<String, String> usersData) throws Exception {
		WebClient webClient = WebClient.create(authEndpoint);
		Mono<String> authResponse = webClient.post().uri("/registration").
				body(BodyInserters.fromValue(usersData)).
				retrieve().bodyToMono(String.class);
		String output = authResponse.block();
		System.out.println(output);
//		if(authResponse.toString().equals("")) {
//			emailService.sendEmail();
//		}
		return output;
	}
}
