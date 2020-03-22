package com.carefirst.email.repository;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;
import com.carefirst.email.config.EmailConfigProperties;
import com.carefirst.email.constant.ApplicationConstants;

@Repository
public class EmailRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailRepository.class);
			
//	@Autowired
//	JavaMailSender javaMailSender;
//	
	@Autowired
	EmailConfigProperties emailConfigProperties;
	
	public String sendMail() {
		LOGGER.info("::::sendMail-->>>>> Start:::::::::::");
		String errorMessage = "";
		try {

//			MimeMessage message = javaMailSender.createMimeMessage();
//			MimeMessageHelper mailMessage = new MimeMessageHelper(message, true);
//			String[] recipientList = emailConfigProperties.getTo().split(ApplicationConstants.COMMA);
//			InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
//			int counter = 0;
//			LOGGER.debug("Email To :" + recipientList);
//			for (String recipient : recipientList) {
//				recipientAddress[counter] = new InternetAddress(recipient.trim());
//				counter++;
//			}
//			mailMessage.setTo(recipientAddress);
//			mailMessage.setFrom(emailConfigProperties.getFrom());
//			mailMessage.setSubject(emailConfigProperties.getSubject());
//			mailMessage.setText(emailHTMLContent(), true);
//			javaMailSender.send(message);
			errorMessage = "Successfully email sent.";
		} catch (Exception e) {
			errorMessage = "Email sending failed";
			LOGGER.error("Error Occuring while sending the mail ::::::::::::::::: "+e.getMessage());
		}

		LOGGER.info("::::sendMail-->>>>> End:::::::::::");
		return errorMessage;
	}

	protected String emailHTMLContent() {
		String message = "";

		try {
			StringBuffer buffer = new StringBuffer();
			message = ApplicationConstants.HTML_HEAD;
			message += ApplicationConstants.HTML_BR_TAG + emailConfigProperties.getContent() + ApplicationConstants.HTML_BR_TAG_END;
			message += ApplicationConstants.HTML_BR_TAG +  ApplicationConstants.HTML_BR_TAG_END;
			buffer.append(ApplicationConstants.HTML_BR_TAG_END);
			message += buffer.toString();
			LOGGER.info(">>>Email body content :::::() -> " + message);
		} catch (Exception e) {
			LOGGER.error("Error Occuring while constructing the html content ::::::: "+e.getMessage());
		}
		return message;
	}
	
}
