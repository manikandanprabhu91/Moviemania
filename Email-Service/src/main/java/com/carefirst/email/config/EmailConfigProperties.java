package com.carefirst.email.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "email")
public class EmailConfigProperties {

	private String successmessage;
	private String subject;
	private String from;
	private String content;
	private String activatemsg;
	private String to;
	
	public String getSuccessmessage() {
		return successmessage;
	}
	public void setSuccessmessage(String successmessage) {
		this.successmessage = successmessage;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getActivatemsg() {
		return activatemsg;
	}
	public void setActivatemsg(String activatemsg) {
		this.activatemsg = activatemsg;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}

}
