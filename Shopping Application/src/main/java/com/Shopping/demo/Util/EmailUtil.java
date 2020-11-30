package com.Shopping.demo.Util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;

import com.Shopping.demo.Constants.EmailMessageConstant;

public class EmailUtil {
	@Async
	
	public void sendmail(String password, String recipientMailId, String fullName, String imageUrl)
			throws AddressException, MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("noreplywolfie@gmail.com", "noreplywolfie123");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("noreplywolfie@gmail.com", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientMailId));
		msg.setSubject(EmailMessageConstant.EMAIL_SUBJECT);
		msg.setContent(String.format(EmailMessageConstant.EMAIL_WELCOME_MESSAGE, fullName), "text/html");
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(String.format(EmailMessageConstant.EMAIL_WELCOME_MESSAGE, fullName), "text/html");
		final BufferedImage image = ImageIO
				.read(new URL("https://www.bigcommerce.com/blog/wp-content/uploads/2017/08/welcome-emails-hero.jpg"));

		Graphics g = image.getGraphics();
		g.setFont(g.getFont().deriveFont(30f));
		g.drawString(String.format(EmailMessageConstant.EMAIL_PASSWORD_MESSAGE, fullName, password), 100, 100);
		g.dispose();

		ImageIO.write(image, "jpg", new File("test.jpg"));

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		MimeBodyPart attachPart = new MimeBodyPart();

		attachPart.attachFile("C:\\Users\\91876\\Downloads\\demo\\demo\\test.jpg");
		multipart.addBodyPart(attachPart);
		msg.setContent(multipart);
		Transport.send(msg);
	}

	public static void main(String[] args) throws Exception {
		final BufferedImage image = ImageIO
				.read(new URL("https://www.bigcommerce.com/blog/wp-content/uploads/2017/08/welcome-emails-hero.jpg"));

		Graphics g = image.getGraphics();
		g.setFont(g.getFont().deriveFont(30f));
		g.drawString("Hello Ankit,\nYour password is ank1234", 100, 100);
		g.dispose();

		ImageIO.write(image, "jpg", new File("test.jpg"));
	}

}
