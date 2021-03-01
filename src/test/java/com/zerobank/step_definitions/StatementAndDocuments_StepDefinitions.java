package com.zerobank.step_definitions;

import com.zerobank.pages.OnlineBankingPage;
import com.zerobank.pages.OnlineStatementsPage;
import static com.zerobank.utilities.BrowserUtils.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class StatementAndDocuments_StepDefinitions {

    OnlineBankingPage onlineBankingPage = new OnlineBankingPage();
    OnlineStatementsPage onlineStatementsPage = new OnlineStatementsPage();

    @Given("the user accesses the Statements & Documents tab")
    public void the_user_accesses_the_statements_documents_tab() {
       onlineBankingPage.navigateTo("Online Statements");
    }
    @When("the user selects the Recent Statements Year {int}")
    public void the_user_selects_the_recent_statements_year(Integer int1) {
        onlineStatementsPage.selectAYear(int1);
    }
    @Then("{int} statements should be displayed for that year")
    public void statements_should_be_displayed_for_that_year(Integer int1) {
        assertEquals(int1,onlineStatementsPage.getNumberOfStatements());
    }

    @When("the user clicks on statement {string}")
    public void theUserClicksOnStatement(String fileName) {
        onlineStatementsPage.downloadFile(fileName);
    }

    @Then("the downloaded file name should contain {string}")
    public void theDownloadedFileNameShouldContain(String file) {
        assertTrue(onlineStatementsPage.isFileDownloaded(file));
    }

    @And("the file type should be pdf")
    public void theFileTypeShouldBePdf() {
        assertEquals(onlineStatementsPage.getDownloadedType(),"pdf");
    }
}
