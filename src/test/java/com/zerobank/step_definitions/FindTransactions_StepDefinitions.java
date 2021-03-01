package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.pages.OnlineBankingPage;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;


public class FindTransactions_StepDefinitions {

    OnlineBankingPage onlineBankingPage = new OnlineBankingPage();
    AccountActivityPage accountActivityPage = new AccountActivityPage();

    @When("the user enters date range from {string} to {string}")
    public void the_user_enters_date_range_from_to(String from, String to) {
        accountActivityPage.enterDate("from",from);
        accountActivityPage.enterDate("to",to);
    }
    @When("clicks search")
    public void clicks_search() {
      accountActivityPage.findSearch();
    }
    @Then("results table should only show transactions dates between {string} to {string}")
    public void results_table_should_only_show_transactions_dates_between_to(String from, String to) {
        assertTrue(accountActivityPage.isCorrectTransactionDate(from,to));
    }
    @Then("the results should be sorted by most recent date")
    public void the_results_should_be_sorted_by_most_recent_date() {
        assertTrue(accountActivityPage.isSortedByCorrectDate());
    }
    @Then("the results table should only not contain transactions dated {string}")
    public void the_results_table_should_only_not_contain_transactions_dated(String date) {
        LocalDate localDate = accountActivityPage.convertToLocalDate(date);
        List<LocalDate> dates = accountActivityPage.localDates();
        assertFalse(dates.contains(localDate));
    }

    @When("the user enters description {string}")
    public void the_user_enters_description(String string) {
        accountActivityPage.descriptionBox.sendKeys(string.toUpperCase());
    }
    @Then("results table should only show descriptions containing {string}")
    public void results_table_should_only_show_descriptions_containing(String string) {
       assertTrue(accountActivityPage.descriptionCheck(string));
    }

    @Then("results table should show at least one result under {string}")
    public void resultsTableShouldShowAtLeastOneResultUnder(String type) {
        int numberOfResult = accountActivityPage.numberOfResults(type);
        assertTrue(numberOfResult>=1);
    }

    @When("user selects type {string}")
    public void userSelectsType(String type) {
        accountActivityPage.selectType(type);
    }

    @But("results table should show no result under {string}")
    public void resultsTableShouldShowNoResultUnder(String type) {
        int numberOfResult = accountActivityPage.numberOfResults(type);
        assertEquals(0, numberOfResult);
    }
}
