package com.dbs.stepDefinitions;

import basePackage.BaseClass;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.ExcelAPI;
import utility.ScreenshotHelper;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

public class AwardsAccoladesSteps extends BaseClass {

    Logger logger = LogManager.getLogger(AwardsAccoladesSteps.class.getName());

    /**
     * Page Factory with @CacheLookup for faster retrieval of WebElements
     */
    @CacheLookup @FindBy(xpath = "//a[contains(text(),'Who We Are')]") WebElement whoWeAreTab;
    @CacheLookup @FindBy(xpath = "//a[contains(text(),'Our Awards & Accolades')]") WebElement ourAwardsAndAccoladesLink;
    @CacheLookup @FindBy(xpath = "//p[contains(text(),'Here are some of our awards.')]") WebElement ourAwardsAndAccoladesPage;
    @CacheLookup @FindBy(xpath = "//div[@class='row mBot-20']") List<WebElement> totalAwardsContainers;

    @Given("Already on Awards & Accolades page")
    public void already_on_awards_accolades_page() throws IOException, InterruptedException {
        PageFactory.initElements(driver, this);
        logger.info("Navigate to Awards and Accolades");
        whoWeAreTab.click();
        waitForElement(ourAwardsAndAccoladesLink, Duration.ofSeconds(2000));
        ourAwardsAndAccoladesLink.click();
        ScreenshotHelper.takeScreenshot("AwardsAccolades_Page_Check", driver);
        Assert.assertEquals("Banking Awards & Accolades | DBS Bank", driver.getTitle());
    }

    @Given("The awards are displayed")
    public void the_awards_are_displayed() throws IOException, InterruptedException {
        waitForElement(ourAwardsAndAccoladesPage, Duration.ofSeconds(5000));
        ScreenshotHelper.takeScreenshot("Awards_Displayed_Check", driver);
        Assert.assertTrue(ourAwardsAndAccoladesPage.isDisplayed());
    }

    @Then("Verify the total awards displayed are {int}")
    public void verify_the_total_awards_displayed_are(Integer totalAwards) {
        Assert.assertSame(totalAwardsContainers.size(), totalAwards);
    }

    @Then("Validate all awards names and captions")
    public void validate_all_awards_names_and_captions() throws IOException {
        ExcelAPI excelAPI = new ExcelAPI("src/excelExportAndFileIO/FBBusiness.xlsx");
        Multimap<String, String> cellMap = ArrayListMultimap.create();
        int excelRowAwards = excelAPI.getRowCount("awardsName");
        int excelColumnAwards = excelAPI.getColumnCount("awardsName");
        for(int i = 0; i <= excelRowAwards; i++){
            String cellValueKey = excelAPI.getCellData("awardsName", 0, i);
            for(int j = 0; j < excelColumnAwards; j++){
                String cellValueValue = excelAPI.getCellData("awardsName", 1, i);
                cellMap.put(cellValueKey, cellValueValue);
            }
        }
        Iterator<WebElement> iterator = totalAwardsContainers.iterator();
        int validAwards = 0;
        while (iterator.hasNext() && validAwards<6) {
            WebElement eachAward = iterator.next();
            String awardName = eachAward.getText().split("\n")[0];
            String awardCaption = eachAward.getText().split("\n")[1];
            if(cellMap.containsKey(awardName) && cellMap.containsValue(awardCaption)){
                logger.info(awardName + "----------" + awardCaption);
                validAwards++;
            }
        }
        Assert.assertEquals(6, validAwards);
        tearDown();
    }
}