package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static com.zerobank.utilities.BrowserUtils.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountActivityPage {

    public AccountActivityPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//select[@id='aa_accountId']")
    public WebElement accountDropdown;

    @FindBy(xpath = "//div[@id='tabs']//a")
    public List<WebElement> TransactionsTab;

    @FindBy(xpath = "//input[@id='aa_fromDate']")
    public WebElement from_xpath;

    @FindBy(xpath = "//input[@id='aa_toDate']")
    public WebElement to_xpath;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement findButton;

    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']//thead//th")
    public List<WebElement> results;

    @FindBy(xpath = "//input[@id='aa_description']")
    public WebElement descriptionBox;

    @FindBy(xpath = "//select[@id='aa_type']")
    public WebElement typeDropdown;

    public void enterDate(String type, String date){
        if(type.equalsIgnoreCase("from")){
            from_xpath.sendKeys(date);
        }else if(type.equalsIgnoreCase("to")){
            to_xpath.sendKeys(date);
        }else throw new RuntimeException("Please type a correct date type");
    }

    public void navigateTo(String type){
        for (WebElement each : TransactionsTab) {
            if(each.getText().equalsIgnoreCase(type)){
                click(each);
                break;
            }
        }
    }

    public LocalDate convertToLocalDate(String date){
        String[] strDates = date.split("-");
        int[] dates = new int[3];
        for (int i = 0; i < dates.length; i++) {
            dates[i]=Integer.parseInt(strDates[i]);
        }
        return LocalDate.of(dates[0],dates[1],dates[2]);
    }

   public int rowIndex(String type){
        int index = 1;
       for (WebElement each : results) {
           if(each.getText().equalsIgnoreCase(type)){
             break;
           }
           index++;
       }
       return index;
   }

   public List<String> resultsText(String type){
        String xpath ="//div[@id='filtered_transactions_for_account']//td[%s]";
        List<WebElement> elements = Driver.getDriver().findElements(By.xpath(String.format(xpath,rowIndex(type))));
        return getListOfElementText(elements);
   }
   
   public List<LocalDate> localDates(){
        List<LocalDate> dates = new ArrayList<>();
       for (String each : resultsText("Date")) {
           dates.add(convertToLocalDate(each));
       }
       return dates;
   }

   public boolean isCorrectTransactionDate(String from, String to){
        LocalDate fromDate = convertToLocalDate(from);
        LocalDate toDate = convertToLocalDate(to);
       for (LocalDate localDate : localDates()) {
           if(localDate.isBefore(fromDate)||localDate.isAfter(toDate)){
               return false;
           }
       }
       return true;
   }

   public boolean isSortedByCorrectDate(){
       for (int i = 0; i < localDates().size() - 1; i++) {
          if (localDates().get(i).isBefore(localDates().get(i+1)))
               return false;
       }
       return true;
   }

   public void findSearch(){
        click(findButton);
        from_xpath.clear();
        to_xpath.clear();
        descriptionBox.clear();
   }

   public boolean descriptionCheck(String type){
       for (String each : resultsText("Description")) {
           if(!each.contains(type)){
               return false;
           }
       }
       return true;
   }

   public int numberOfResults(String type){
        int count=0;
       for (String each : resultsText(type)) {
           if(!each.isEmpty()){
               count++;
           }
       }
       return count;
   }

   public void selectType(String type){
       Select select = new Select(typeDropdown);
       select.selectByVisibleText(type);
   }
}
