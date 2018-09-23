package ru.vladigeras.masterpizzasale.constant;

import ru.vladigeras.masterpizzasale.util.StringUtil;

/**
 * @author vladi_geras on 23.09.2018
 */
public class MailTemplate {
	public static final String SUBJECT = "MASTERPIZZA";

	private MailTemplate() {
	}

	public static String getGreeting(String name) {
		if (StringUtil.nullOrEmptyTrimmed(name)) return "Здравствуйте!";

		return "Здравствуйте, " + name + "!";
	}

	public static String getGoodBye() {
		return "С уважением, " + SUBJECT;
	}

}
