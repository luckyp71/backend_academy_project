package com.training.services_impl;

import org.springframework.stereotype.Service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.training.models.EmailConfig;
import com.training.models.NewsUserDTO;
import com.training.services.EmailService;

@Service
public class EmailServiceImpl extends Thread implements EmailService {
	
	private String username;
	private String email;
	private String password;
	
	public EmailServiceImpl() {
		
	}
	
	public EmailServiceImpl(NewsUserDTO userDTO) {
		this.username = userDTO.getUsername();
		this.email = userDTO.getEmail();
		this.password = userDTO.getPassword();
	}
	

	@Override
	public void run() {
		this.sendEmail();
	}

	@Override
	public void sendEmail() {
		EmailConfig emailConfig = new EmailConfig();
		Properties props = new Properties();
		props.put("mail.smtp.host", emailConfig.getHost());
		props.put("mail.smtp.socketFactory.port", emailConfig.getPort());
		props.put("mail.smtp.socketFactory.class", emailConfig.getSsl());
		props.put("mail.smtp.auth", emailConfig.getAuth());
		props.put("mail.smtp.port", emailConfig.getSmtpPort());

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("backendacademy@gmail.com", "backendacademy2019");
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("backendacademy@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.email));
			message.setSubject("Forgot Password");
			message.setText("Dear "+this.username+",\n\n Here is your new password: "+this.password);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
