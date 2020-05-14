package com.qk.usermgt.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qk.usermgt.dto.Email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Component
public class MailUtil {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void send(Email email)
	{
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(email.getTo());
		message.setSubject(email.getSubject());
		message.setText(email.getBody());
		
		javaMailSender.send(message);
		System.out.println("email send successfully..!!");
	}

}
