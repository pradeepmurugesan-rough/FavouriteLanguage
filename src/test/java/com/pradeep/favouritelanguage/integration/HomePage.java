package com.pradeep.favouritelanguage.integration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Pmuruge on 12/28/2015.
 */
public class HomePage {

    WebDriver driver;
    public static final String usernameId = "username";
    public static final String submitButton = "button[type='submit']";
    public static final String resultDiv = "alert";
    By username = By.id(usernameId);
    By submit = By.cssSelector(submitButton);
    By result = By.className(resultDiv);

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUsername(String user) {
        driver.findElement(username).sendKeys(user);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void submitAndWait() {
        driver.findElement(submit).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(result));
    }

    public String getResultText() {
        return driver.findElement(result).getText();
    }

}
