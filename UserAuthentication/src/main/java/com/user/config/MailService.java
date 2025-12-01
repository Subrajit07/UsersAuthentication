package com.user.config;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.user.dto.MailParameters;
import com.user.handler.MailServiceException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Component
public class MailService {

	private final JavaMailSender mailSender;
	@Value("${email.from}")
	private String fromEmail;
	
	public void sendMail(MailParameters params){
		MimeMessage mm = mailSender.createMimeMessage();
		try {
			var helper = new MimeMessageHelper(mm, true);
		helper.setFrom(fromEmail);
		helper.setTo(params.to());
		helper.setSubject(params.subject());
		helper.setText(params.body(), params.isHtml());
		mailSender.send(mm);
		}catch (MessagingException e) {
            log.error("Failed to send email", e);
            throw new MailServiceException("Failed to send email "+ e);
        }
	}
	
}
