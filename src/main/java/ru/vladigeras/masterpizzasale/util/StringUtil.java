package ru.vladigeras.masterpizzasale.util;

/**
 * @author vladi_geras on 23.09.2018
 */
public class StringUtil {

	private StringUtil() {
	}

	public static boolean nullOrEmptyTrimmed(String str) {
		return str == null || str.trim().isEmpty();
	}

	public static String trimIfNotNull(String str) {
		return str != null ? str.trim() : null;
	}
}
