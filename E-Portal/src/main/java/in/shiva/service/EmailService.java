package in.shiva.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void simpleMailSender(String subject, String body, String to) {
		try {
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setSubject(subject);
			mail.setText(body);
			mail.setTo(to);

			mailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mimeMailSender(String subject, String body, String to) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setSubject(subject);
			helper.setText(to, true);
			helper.setText(to);
			helper.addAttachment("check.csv", new File("F:\\45-SBMS\\check.csv"));

			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

