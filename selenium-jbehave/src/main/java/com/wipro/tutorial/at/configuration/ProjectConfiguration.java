package com.wipro.tutorial.at.configuration;

import org.jbehave.web.selenium.PropertyWebDriverProvider;
import org.jbehave.web.selenium.WebDriverProvider;
import org.jbehave.web.selenium.WebDriverScreenshotOnFailure;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


@Configuration
@ComponentScan({"com.wipro.tutorial"})
@PropertySource("classpath:configs/env.properties")
public class ProjectConfiguration {

	@Bean
	public static PropertySourcesPlaceholderConfigurer getPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public WebDriverProvider webDriverProvider() {		
		WebDriverProvider webDriverProvider = new CustomPropertyWebDriver();
		
		System.setProperty("browser", "chrome");
		if (System.getProperty("webdriver.chrome.driver") == null ) {
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
		}
		
		return webDriverProvider;
	}
		
	@Bean
	public WebDriverScreenshotOnFailure screenshotOnFailureDriver() {
		return new WebDriverScreenshotOnFailure(webDriverProvider());
	}

	class CustomPropertyWebDriver extends PropertyWebDriverProvider{
		@Override
		protected ChromeDriver createChromeDriver() {
			String dataPath = "/home/lodek/.config/chromium";
			ChromeOptions options = new ChromeOptions();
			options.addArguments(String.format("user-data-dir=%s", dataPath));
			options.addArguments("--start-maximized");
			return new ChromeDriver(options);
        }
	}

}
