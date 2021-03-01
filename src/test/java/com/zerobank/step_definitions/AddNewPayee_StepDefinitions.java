package com.zerobank.step_definitions;

import com.zerobank.pages.OnlineBankingPage;
import com.zerobank.pages.PayBillsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;
import java.util.Map;
import static com.zerobank.utilities.BrowserUtils.*;

public class AddNewPayee_StepDefinitions {

    OnlineBankingPage onlineBankingPage = new OnlineBankingPage();
    PayBillsPage payBillsPage = new PayBillsPage();

    @When("creates new payee using following information")
    public void creates_new_payee_using_following_information(Map<String,String> dataTable) {
        payBillsPage.addNewPayee(dataTable);
    }
    @Then("message {string} should be displayed")
    public void message_should_be_displayed(String msg) {
        String actualMsg = payBillsPage.successMsg.getText();
        assertEquals(msg,actualMsg);
    }

    @When("user tries to calculate cost without selecting a currency")
    public void userTriesToCalculateCostWithoutSelectingACurrency() {
        payBillsPage.enterAmount(50);
        click(payBillsPage.calculateCost);
    }

    @Then("alert message {string} should be displayed")
    public void alertMessageShouldBeDisplayed(String alertMsg) {
        assertEquals(alertMsg,payBillsPage.getAlertMsg());
    }

    @When("user tries to calculate cost without entering a value")
    public void userTriesToCalculateCostWithoutEnteringAValue() {
        payBillsPage.selectACurrency();
        click(payBillsPage.calculateCost);
    }

    @And("the user is on {string} page")
    public void theUserIsOnPage(String page) {
        onlineBankingPage.navigateTo(page);
    }

    @And("the user access to the {string} tab")
    public void theUserAccessToTheTab(String tab) {
        payBillsPage.navigateTo(tab);
    }
}
