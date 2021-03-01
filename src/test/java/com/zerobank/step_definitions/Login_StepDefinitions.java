package com.zerobank.step_definitions;

import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;
import static com.zerobank.utilities.BrowserUtils.*;


public class Login_StepDefinitions {

    LoginPage loginPage = new LoginPage();

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @When("login with valid credentials")
    public void login_with_valid_credentials() {
        loginPage.login();
    }

    @When("user enters {string} and {string}")
    public void user_enters_and(String username, String password) {
       loginPage.login(username,password);
    }
    @Then("error message {string} should be displayed")
    public void error_message_should_be_displayed(String errorMsg) {
        waitUntilVisibilityOf(loginPage.errorMsgElement);
        String actualErrorMsg = loginPage.errorMsgElement.getText();
        assertEquals(errorMsg,actualErrorMsg);
    }

    @Then("title should be {string}")
    public void titleShouldBe(String title) {
        String actualTitle = Driver.getDriver().getTitle();
        assertEquals(title,actualTitle);
    }
}
