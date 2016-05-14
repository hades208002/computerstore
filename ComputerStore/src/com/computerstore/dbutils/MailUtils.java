package com.computerstore.dbutils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	
	private static String userEmail="computer2016store@gmail.com";
	private static String passEmail="password2016carlo";
	
	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		// 1.创建一个程序与邮件服务器会话对象 Session

		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.port", "587");
		
		// 创建验证器
		Session session = Session.getInstance(props, new javax.mail.Authenticator(){
			 protected PasswordAuthentication getPasswordAuthentication(){
				 return new PasswordAuthentication(userEmail,passEmail);
			 }
		 });
		 
		 try{
			 MimeMessage message = new MimeMessage(session);
			 message.setFrom(new InternetAddress("computer2016store"));
			 message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			 message.setSubject("Order Confirmation!");
			 message.setContent( emailMsg,"text/html;charset=utf-8");
			 message.setSentDate(new java.util.Date());
			 Transport.send(message);
			 
		 }
		 catch(MessagingException e){
			 throw new RuntimeException(e);
		 }
	}
}
