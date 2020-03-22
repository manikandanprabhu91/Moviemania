package com.carefirst.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carefirst.email.repository.EmailRepository;

@Service
public class EmailService {

	@Autowired
	EmailRepository emailRepository;
	
	public String sendEmail() throws Exception {
		return emailRepository.sendMail();
	}
	
	
}
