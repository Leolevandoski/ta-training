package com.wipro.tutorial.at.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HomePage extends AbstractPage {
	private final String SEARCH_FIELD_ID = "search-input";
	private final String SEARCH_BUTTON_NAME = "btnK";
	
	@Value("${URL}")
	private String URL;
	
	public void navigateTo() {
		webDriverProvider.get().get(URL);
	}
	
	private WebElement searchField() {
		return webDriverProvider.get().findElement(By.id(SEARCH_FIELD_ID));
	}
	
	public void search(String strSearch) {
		searchField().sendKeys(strSearch);
		searchField().sendKeys(Keys.ENTER);
	}

}



