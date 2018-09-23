package ru.vladigeras.masterpizzasale.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.vladigeras.masterpizzasale.service.MailService;

/**
 * @author vladi_geras on 23.09.2018
 */
@Service
public class MailServiceImpl implements MailService {

	@Value("${spring.mail.username}")
	private String from;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendMail(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		message.setFrom(from);

		try {
			javaMailSender.send(message);
		} catch (Exception e) {
			if (e instanceof MailSendException) {
				MailSendException mailSendException = (MailSendException) e;

				Throwable cause = mailSendException.getMessageExceptions()[0].getCause();

				throw new RuntimeException("Проверьте email. Возможно, его не существует");
			} else {
				throw new RuntimeException(e);
			}
		}
	}
}
