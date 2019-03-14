package com.training.services;

import javax.mail.MessagingException;

public interface EmailService {
	
	public void sendEmail() throws MessagingException;

}
