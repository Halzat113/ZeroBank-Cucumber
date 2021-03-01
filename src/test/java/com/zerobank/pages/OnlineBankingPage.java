package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.zerobank.utilities.BrowserUtils.*;

import java.util.List;

public class OnlineBankingPage {
    public OnlineBankingPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//div[@id='online_banking_features']//div[@class='span4']//span")
    public List<WebElement> accountTypes;

    public void navigateTo(String type){
        for (WebElement each : accountTypes) {
            if(each.getText().equalsIgnoreCase(type)){
                click(each);
                break;
            }
        }
    }

}
