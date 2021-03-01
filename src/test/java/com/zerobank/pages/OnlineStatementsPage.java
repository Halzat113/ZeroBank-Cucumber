package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import com.zerobank.utilities.GlobalDates;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.zerobank.utilities.BrowserUtils.*;

import java.io.File;
import java.util.List;

public class OnlineStatementsPage {

    public OnlineStatementsPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    GlobalDates dates = new GlobalDates();

    @FindBy(xpath = "//ul[@class='nav nav-pills']/li")
    public List<WebElement> yearSelection;

    @FindBy(xpath = "//div[@class='tab-pane active']//tbody/tr//a")
    public List<WebElement> statements;

    public void selectAYear(Integer year){
        for (WebElement each : yearSelection) {
            if(Integer.parseInt(each.getText())==year){
                click(each);
            }
        }
    }

    public Integer getNumberOfStatements(){
      return statements.size();
    }

    public void downloadFile(String fileName){
        for (WebElement each : statements) {
            if(each.getText().equals(fileName)){
                click(each);
                sleep(1);
                break;
            }
        }
    }

    public boolean isFileDownloaded(String fileName){
       String path = "C:\\Users\\halza\\Downloads";
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if(files[i].getName().contains(fileName)){
                dates.setFileName(files[i].getName());
                files[i].deleteOnExit();
                return true;
            }
        }
        return false;
    }

    public String getDownloadedType(){
        return dates.getFileName().substring(dates.getFileName().length()-3);
    }

}
