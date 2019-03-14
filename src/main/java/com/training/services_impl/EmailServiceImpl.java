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
	private String type;

	public EmailServiceImpl() {
		//Default constructor, leave it blank
	}

	public EmailServiceImpl(NewsUserDTO userDTO, String type) {
		this.username = userDTO.getUsername();
		this.email = userDTO.getEmail();
		this.password = userDTO.getPassword();
		this.type = type;
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
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("backendacademy@gmail.com", "backendacademy2019");
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("backendacademy@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.email));
			if (type.equalsIgnoreCase("register")) {
				message.setSubject("User Registeration");
				message.setText("Dear " + this.username + ",\n\n You're successfully registered");
			}
			else if (type.equalsIgnoreCase("forgot")) {
				message.setSubject("Forgot Password");
				message.setText("Dear " + this.username + ",\n\n Here is your new password: " + this.password);
			}
			Transport.send(message);
		} catch (MessagingException e) {
			e.getLocalizedMessage();
		}
	}
}
