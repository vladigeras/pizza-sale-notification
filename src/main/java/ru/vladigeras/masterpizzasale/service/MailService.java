package ru.vladigeras.masterpizzasale.service;

import org.springframework.scheduling.annotation.Async;

/**
 * @author vladi_geras on 23.09.2018
 */
public interface MailService {

	@Async
	void sendMail(String to, String subject, String text);
}
