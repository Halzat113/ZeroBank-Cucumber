package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.zerobank.utilities.BrowserUtils.*;

import java.util.List;

public class AccountSummaryPage {
    public AccountSummaryPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//div[@class='offset2 span8']//a")
    public List<WebElement> links;

    public void navigateTo(String type){
        for (WebElement each : links) {
            if(each.getText().equalsIgnoreCase(type)){
                click(each);
                break;
            }
        }
    }
}
