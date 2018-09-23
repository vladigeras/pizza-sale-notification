package ru.vladigeras.masterpizzasale.service.impl;

import org.json.simple.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.vladigeras.masterpizzasale.constant.PizzaConstant;
import ru.vladigeras.masterpizzasale.service.CafeService;
import ru.vladigeras.masterpizzasale.util.StringUtil;

import java.io.IOException;

/**
 * @author vladi_geras on 23.09.2018
 */
@Service
public class MasterPizzaObninskService implements CafeService {

	private static Logger logger = LoggerFactory.getLogger(MasterPizzaObninskService.class);

	private String pizzaUrl = "http://obninskpizza.ru/menu/klassicheskie-i-firmennye-piccy";
	private String saleDivId = "discounttime_wrapper";
	private String summaryInfoDivClass = "summary info";
	private String pizzaTitleTagClass = "title";
	private String pizzaPriceTagClass = "price";

	@Override
	public JSONObject parsePage() {
		JSONObject result = new JSONObject();

		try {
			Document page = prepareForRequest(pizzaUrl).get();

			Element summaryInfo = page.getElementById(this.saleDivId).getElementsByClass(summaryInfoDivClass).first();
			String title = summaryInfo.getElementsByClass(pizzaTitleTagClass).first().text();

			Elements pricesTags = summaryInfo.getElementsByClass(pizzaPriceTagClass);

			String priceOld = pricesTags.first().text().split(" ")[0];
			String price = pricesTags.last().text().split(" ")[0];

			result.put(PizzaConstant.PIZZA_NAME, title);
			result.put(PizzaConstant.PIZZA_PRICE_OLD, priceOld);
			result.put(PizzaConstant.PIZZA_PRICE_NEW, price);

		} catch (IOException e) {
			logger.error("Произошла ошибка: " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	private Connection prepareForRequest(String url) {
		if (StringUtil.nullOrEmptyTrimmed(url)) {
			logger.error("Не указан URL");
			throw new RuntimeException("Не указан URL");
		}

		return Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
	}
}
