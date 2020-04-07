package com.wipro.tutorial.at.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HomePage extends AbstractPage {
	private final String SEARCH_INPUT_ID = "search-input";
	private final String SEARCH_BUTTON_SELECTOR = "#header-content > header > div.breakpoint.main-bar > section.search > section > form > div > button";
	
	@Value("${URL}")
	private String URL;
	
	public void navigateTo() {
		webDriverProvider.get().get(URL);
	}
	
	private WebElement searchField() {
		return webDriverProvider.get().findElement(By.id(SEARCH_INPUT_ID));
	}
	
	public void search(String strSearch) {
		searchField().sendKeys(strSearch);
	}

	public void clickSearch() {
		waitElementToBeClickable(By.cssSelector(SEARCH_BUTTON_SELECTOR));
	}
}


