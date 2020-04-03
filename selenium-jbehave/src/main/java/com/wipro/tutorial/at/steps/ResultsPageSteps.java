package com.wipro.tutorial.at.steps;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.wipro.tutorial.at.pages.ResultsPage;

@Component
public class ResultsPageSteps extends AbstractSteps {

	@Autowired
	private ResultsPage resultsPage;


	@When("I should see first result as '$firstResult'")
	public void IShouldSeeFirstResultAs(@Named("firstResult") String titleExpected) {
		assertEquals(titleExpected, resultsPage.firstResultText());
	}

	@When("I should click on the product")
	public void IShouldClickOnProduct () {
		resultsPage.firstResultText();
	}

	@Then("I should buy the product")
	public void iShouldBuyProduct () {
		resultsPage.getBuyProduct();
	}
}