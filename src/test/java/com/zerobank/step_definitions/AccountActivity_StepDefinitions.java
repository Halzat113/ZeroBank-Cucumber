package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.LoginPage;
import com.zerobank.pages.OnlineBankingPage;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;


import static com.zerobank.utilities.BrowserUtils.*;

public class AccountActivity_StepDefinitions {

    LoginPage loginPage = new LoginPage();
    OnlineBankingPage onlineBankingPage = new OnlineBankingPage();
    AccountSummaryPage accountSummaryPage = new AccountSummaryPage();
    AccountActivityPage accountActivityPage = new AccountActivityPage();

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.login();
    }

    @When("the user clicks on {string} link on the Account Summary page")
    public void theUserClicksOnLinkOnTheAccountSummaryPage(String type) {
        onlineBankingPage.navigateTo("Account Summary");
        accountSummaryPage.navigateTo(type);
    }

    @Then("the Account Activity page should be displayed with title {string}")
    public void theAccountActivityPageShouldBeDisplayedWithTitle(String title) {
        String actualTitle = getCurrentPageTitle();
        assertEquals(title, actualTitle);
    }
    @Then("Account drop down should have {string} selected")
    public void account_drop_down_should_have_selected(String type) {
        WebElement dropdown = accountActivityPage.accountDropdown;
        String actualText = getSelectedTextFromDropdown(dropdown);
        assertEquals(type,actualText);
    }
}
