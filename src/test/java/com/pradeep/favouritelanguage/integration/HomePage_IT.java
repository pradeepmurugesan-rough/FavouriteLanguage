package com.pradeep.favouritelanguage.integration;

import com.pradeep.favouritelanguage.util.Constants;
import com.pradeep.favouritelanguage.util.TestUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Created by Pmuruge on 12/28/2015.
 */
public class HomePage_IT {

    private WebDriver driver;
    private HomePage homePage;
    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("http://localhost:9090/");
        homePage = new HomePage(this.driver);
    }
    @Test
    public void testAppStarted() {
        Assert.assertEquals("Favourite Language", homePage.getTitle());
    }

    @Test
    public void testValidUser() {
        homePage.setUsername(TestUtils.validUser);
        homePage.submitAndWait();
        Assert.assertTrue(homePage.getResultText().contains("Java"));
    }

    @Test
    public void testInValidUser() {
        homePage.setUsername(TestUtils.inValidUser);
        homePage.submitAndWait();
        Assert.assertTrue(homePage.getResultText().equals(Constants.ERROR_USER_INVALID));
    }

    @Test
    public void testNullLanguageUser() {
        homePage.setUsername(TestUtils.nullRepoUser);
        homePage.submitAndWait();
        Assert.assertTrue(homePage.getResultText().equals(Constants.ERROR_NULL_REPO));
    }

    @Test
    public void testZeroRepoUser() {
        homePage.setUsername(TestUtils.emptyRepoUser);
        homePage.submitAndWait();
        Assert.assertTrue(homePage.getResultText().equals(Constants.ERROR_EMPTY_REPO));
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
