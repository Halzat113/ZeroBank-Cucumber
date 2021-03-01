package com.zerobank.step_definitions;

import com.zerobank.pages.OnlineBankingPage;
import com.zerobank.pages.PayBillsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

import static com.zerobank.utilities.BrowserUtils.*;

public class PurchaseForeignCurrency_StepDefinitions {

    OnlineBankingPage onlineBankingPage = new OnlineBankingPage();
    PayBillsPage payBillsPage = new PayBillsPage();

    @When("the user clicks on Currency tab")
    public void the_user_clicks_on_currency_tab() {
        click(payBillsPage.currencyTab);
    }

    @Then("following currencies should be available")
    public void following_currencies_should_be_available(List<String> currencies) {
        Assert.assertTrue(payBillsPage.getAllOptionsText().containsAll(currencies));
    }

}
