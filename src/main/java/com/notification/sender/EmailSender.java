package com.notification.sender;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.notification.model.Notification;

/**
 * Notification sender class implementation for email channel
 * This contains mock implementation
 * @author Hiral Salvi
 *
 */
@Component("emailSender")
public class EmailSender extends MessageSender {

	private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

	@Override
	public void send(Notification notification) {
			logger.info("Email message Sent for " + notification);
	}
		
	public SimpleMailMessage getMessage(Notification n) {
		SimpleMailMessage message = new SimpleMailMessage(); 
		message.setFrom(n.getFrom()); 
        message.setTo(n.getTo()); 
        message.setSubject(n.getSubject()); 
        message.setText(n.getMessage());
        return message;
	}
	
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	     
	    mailSender.setUsername("XXXXX");
	    mailSender.setPassword("YYYYY");
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	     
	    return mailSender;
	}

	@Override
	public void setChannel() {
		this.channel="email";
	}
	
}
