package com.zerobank.pages;

import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.zerobank.utilities.BrowserUtils.*;

public class LoginPage {
    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//input[@id='user_login']")
    public WebElement loginBox;

    @FindBy(xpath = "//input[@id='user_password']")
    public WebElement passwordBox;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement signInButton;

    @FindBy(xpath = "//div[@class='alert alert-error']")
    public WebElement errorMsgElement;

    public void login(){
        loginBox.sendKeys(ConfigurationReader.getProperty("username"));
        passwordBox.sendKeys(ConfigurationReader.getProperty("password"));
        click(signInButton);
        click(Driver.getDriver().findElement(By.xpath("//button[@id='primary-button']")));
        click(Driver.getDriver().findElement(By.xpath("//li[@id='onlineBankingMenu']/div")));
    }

    public void login(String username,String password){
        loginBox.sendKeys(username);
        passwordBox.sendKeys(password);
        click(signInButton);
        try {
            timeOff();
            Driver.getDriver().findElement(By.xpath("//button[@id='primary-button']")).click();
        }catch (NoSuchElementException e){
           timeOn();
        }
    }
}
