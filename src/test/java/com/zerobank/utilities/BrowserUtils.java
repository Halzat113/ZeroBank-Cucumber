package com.zerobank.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BrowserUtils {

    public static void sleep(int sec){
        try {
            Thread.sleep(sec*1000L);
        }catch (InterruptedException e){}
    }

    public static void click(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),10);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static List<String> getListOfElementText(List<WebElement> WebElement){
        List<String> elements = new ArrayList<>();
        for (WebElement each: WebElement) {
            elements.add(each.getText());
        }
        return elements;
    }

    public static void waitUntilVisibilityOf(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void timeOff(){
        Driver.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    public static void timeOn(){
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static String getCurrentPageTitle() {
        return Driver.getDriver().getTitle();
    }

    public static String getSelectedTextFromDropdown(WebElement element){
        waitUntilVisibilityOf(element);
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    public static void waitUntilFileIsDownloaded(File file){
        FluentWait<WebDriver> wait = new WebDriverWait(Driver.getDriver(),10);

    }
}
