package ru.vladigeras.masterpizzasale.service.impl;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.vladigeras.masterpizzasale.constant.MailTemplate;
import ru.vladigeras.masterpizzasale.constant.PizzaConstant;
import ru.vladigeras.masterpizzasale.service.CafeService;
import ru.vladigeras.masterpizzasale.service.MailService;
import ru.vladigeras.masterpizzasale.service.ScheduleService;

import java.util.Arrays;
import java.util.List;

/**
 * @author vladi_geras on 23.09.2018
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

	private static Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);
	private List<String> recipientsList = Arrays.asList("gerasimov.v88@gmail.com");

	@Autowired
	private MailService mailService;

	@Autowired
	private CafeService cafeService;

	// each start of 44 minute from 9 to 21 hour (in 24 hours format) only in Saturday and Sunday
	@Scheduled(cron = "0 44 9-21 * * 6-7")
	@Override
	public void getSale() {
		logger.info("Время просмотра новых скидок на пиццу");

		JSONObject result = cafeService.parsePage();

		if (result != null) {
			String pizzaName = result.get(PizzaConstant.PIZZA_NAME).toString();
			String pizzaPrice = result.get(PizzaConstant.PIZZA_PRICE_NEW).toString();
			String pizzaOldPrice = result.get(PizzaConstant.PIZZA_PRICE_OLD).toString();

			String mailBody = "Сообщаем Вам об изменении пиццы со скидкой.\n" +
					"Пицца: " + pizzaName + "\n" +
					"Цена по акции: " + pizzaPrice + " рублей\n" +
					"Старая цена: " + pizzaOldPrice + " рублей";
			String mailText = MailTemplate.getGreeting(null) + "\n" + "\n" + mailBody + "\n" + "\n" + MailTemplate.getGoodBye();

			recipientsList.forEach(recipient -> mailService.sendMail(recipient, MailTemplate.SUBJECT, mailText));
		}
	}
}
