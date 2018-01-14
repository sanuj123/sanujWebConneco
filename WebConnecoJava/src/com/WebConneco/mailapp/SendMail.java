package com.WebConneco.mailapp;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
  
	public static void send(String to, String bodyMessage) {
		
		ResourceBundle rb = ResourceBundle.getBundle("config");
		
		final String userName = rb.getString("emailusername");
		final String password = rb.getString("emailpassword");
		System.out.println("starting mail");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		System.out.println("in mail app");
		Session session = Session.getInstance(props,
				
				new javax.mail.Authenticator() {
			     protected PasswordAuthentication getPasswordAuthentication() {
			    	 return new PasswordAuthentication(userName,password);
			     }
		});
		try {
		
		Transport transport = session.getTransport();	
		 	
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(userName));
		msg.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		msg.setSubject("Abroad Manager Team");
		msg.setText(bodyMessage, "text/html; charset=utf-8");
		
		System.out.println("mail sent");
		
		transport.connect();
		Transport.send(msg);
		transport.close();
		
		System.out.println("Done");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
