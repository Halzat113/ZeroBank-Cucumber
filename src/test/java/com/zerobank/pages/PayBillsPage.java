package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static com.zerobank.utilities.BrowserUtils.*;
import static com.zerobank.utilities.DataUtils.*;

import java.util.List;
import java.util.Map;

public class PayBillsPage {

    public PayBillsPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//ul[contains(@class,'ui-tabs')]/li")
    public List<WebElement> links;

    @FindBy(xpath = "//input[@id='np_new_payee_name']")
    public WebElement nameInput;

    @FindBy(xpath = "//textarea[@id='np_new_payee_address']")
    public WebElement addressInput;

    @FindBy(xpath = "//input[@id='np_new_payee_account']")
    public WebElement accountInput;

    @FindBy(xpath = "//input[@id='np_new_payee_details']")
    public WebElement detailsInput;

    @FindBy(xpath = "//input[@id='add_new_payee']")
    public WebElement addButton;

    @FindBy(xpath = "//div[@id='alert_content']")
    public WebElement successMsg;

    @FindBy(xpath = "//select[@id='pc_currency']")
    public WebElement currencyTab;

    @FindBy(xpath = "//input[@id='pc_calculate_costs']")
    public WebElement calculateCost;

    @FindBy(xpath = "//input[@id='pc_amount']")
    public WebElement amountInput;

    public void navigateTo(String linkText){
        for (WebElement each : links) {
            if(each.getText().equalsIgnoreCase(linkText)){
                click(each);
                break;
            }
        }
    }

    public void addNewPayee(Map<String,String> data){
        nameInput.sendKeys(data.get("Payee Name"));
        addressInput.sendKeys("Payee Address");
        accountInput.sendKeys("Account");
        detailsInput.sendKeys("Payee details");
        click(addButton);
    }

    public List<String> getAllOptionsText(){
        Select select = new Select(currencyTab);
        return getListOfElementText(select.getOptions());
    }

    public String getAlertMsg(){
        return Driver.getDriver().switchTo().alert().getText();
    }

    public void selectACurrency(String country){
        Select select = new Select(currencyTab);
        List<WebElement> elements = select.getOptions();
        for (WebElement each : elements) {
            if(each.getText().contains(country)){
                click(each);
                break;
            }
        }
    }

    public void selectACurrency(){
        Select select = new Select(currencyTab);
        select.selectByIndex(generateSingleNum(1,select.getOptions().size()));
    }

    public void enterAmount(int amount){
       amountInput.sendKeys(amount+"");
    }
}
