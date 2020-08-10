package stepDefinition;

import base.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.ExcelAPI;

import java.io.IOException;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;

public class SocialImpactSteps extends BaseClass {

    /**
     * Logger for info logs, WebDriverWait for elements to load
     */
    Logger logger = LogManager.getLogger(SocialImpactSteps.class.getName());

    /**
     * Page Factory with @CacheLookup for faster retrieval of WebElements
     */
    @CacheLookup @FindBy(xpath = "//p[contains(@class,'dbs-group')]//button[contains(@class,'btn btn-primary')][contains(text(),'Learn More')]") WebElement learnMore;
    @CacheLookup @FindBy(xpath = "//ul[@class='breadcrumb']//a[contains(text(),'Creating Social Impact')]") WebElement socialImpactPage;
    @CacheLookup @FindBy(xpath = "//a[@class='nav-link hasDevice'][contains(text(),'Singapore')]") WebElement citySingapore;
    @CacheLookup @FindBy(xpath = "//a[@class='nav-link hasDevice active']") WebElement citySingaporeIsActive;
    @CacheLookup @FindBy(xpath = "//tr") List<WebElement> rowElements;
    @CacheLookup @FindBy(xpath = "//tr[1]/*") List<WebElement> columnElements;
    @CacheLookup @FindBy(xpath = "//div[@class='navbar-links-left']//a[contains(text(),'About')]") WebElement aboutPageLink;
    @CacheLookup @FindBy(xpath = "//a[contains(text(),'Who We Are')]") WebElement whoWeAreTab;

    @Given("Browser is Open")
    public boolean browserIsOpen() {
        initDriver();
        PageFactory.initElements(driver, this);
        String defaultUrl = prop.getProperty("defaultUrl");
        try {
            logger.info("DBS default page is launched");
            driver.get(defaultUrl);
            Assert.assertEquals("DBS Bank | Singapore", driver.getTitle());
            return driver.getCurrentUrl().equals(prop.getProperty("defaultUrl"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @And("User is already on Sustainability Creating Social Impact page")
    public void user_is_already_on_sustainability_creating_social_impact_page() {
        waitForElement(learnMore, Duration.ofSeconds(2000));
        learnMore.click();
        Assert.assertTrue(socialImpactPage.isDisplayed());
    }

    @When("Singapore F&B businesses data is available and displayed for user")
    public void singapore_f_b_businesses_data_is_available_and_displayed_for_user() {
        waitForElement(citySingapore, Duration.ofSeconds(2000));
        citySingapore.click();
        Assert.assertTrue(citySingaporeIsActive.isEnabled());
    }

    @Then("Read & retrieve the table data from cells and write to excel")
    public void read_retrieve_the_table_data_from_cells_and_write_to_excel() throws IOException {
        ExcelAPI excelAPI = new ExcelAPI("src/excelExportAndFileIO/FBBusiness.xlsx");
        int tableRowCount = rowElements.size();
        int tableColumnCount = columnElements.size();
        logger.info("Table row count:\t" + tableRowCount);
        logger.info("Table column count:\t" + tableColumnCount);

        LinkedHashMap<String, String> cellMap = new LinkedHashMap<>();
        for(int i = 1; i <= tableRowCount; i++){
            for(int j = 1; j <= tableColumnCount; j++){
                String cellName = i + "," + j;
                String cellText = driver.findElement(By.xpath("//tr[" + i + "]//td[" + j + "]")).getText();
                cellMap.put(cellName, cellText);
                excelAPI.setCellData("tableOne", j-1, i-1, cellText);
            }
        }
        logger.info("Verifying that the web table is displayed - Map size should be greater than zero");
        Assert.assertTrue(cellMap.size() != 0);
        logger.info("Web Table size is " + cellMap.size());
    }

    @Then("Validate the excel file size is not zero")
    public void validate_the_file_size_is_not_zero() throws IOException {
        logger.info("Verifying the number of rows and columns in the excel file - should be equal to row, column count of web table");
        int tableRowCount = rowElements.size();
        int tableColumnCount = columnElements.size();
        ExcelAPI excelAPI = new ExcelAPI("src/excelExportAndFileIO/FBBusiness.xlsx");
        int excelRowCount = excelAPI.getRowCount("tableOne");
        int excelColumnCount = excelAPI.getColumnCount("tableOne");
        logger.info("Excel sheet row count:\t" + excelRowCount);
        logger.info("Excel sheet column count:\t" + excelColumnCount);
        Assert.assertEquals(excelRowCount, tableRowCount);
        Assert.assertEquals(excelColumnCount, tableColumnCount);
    }

    @And("Navigate to About Page")
    public void navigate_to_about_page() {
        logger.info("Navigating to About page..");
        waitForElement(aboutPageLink, Duration.ofSeconds(2000));
        aboutPageLink.click();
        waitForElement(whoWeAreTab, Duration.ofSeconds(2000));
        Assert.assertTrue(whoWeAreTab.isDisplayed());
        tearDown();
    }
}