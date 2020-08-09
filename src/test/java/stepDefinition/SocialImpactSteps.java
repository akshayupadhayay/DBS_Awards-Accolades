package stepDefinition;

import base.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.joda.time.Seconds;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Util;

import java.time.Duration;
import java.util.ArrayList;
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

    @Then("Read & retrieve the table data from cells")
    public void read_retrieve_the_table_data_from_cells() {
        int tableRowCount = rowElements.size();
        int tableColumnCount = columnElements.size();
        System.out.println("Table row count:\t" + tableRowCount);
        System.out.println("Table column count:\t" + tableColumnCount);

        LinkedHashMap<String, String> cellData = new LinkedHashMap<>();
        for(int i = 1; i <= tableRowCount; i++){
            for(int j = 1; j <= tableColumnCount; j++){
                String cellName = i + "," + j;
                String cellText = driver.findElement(By.xpath("//tr[" + i + "]//td[" + j + "]")).getText();
                cellData.put(cellName, cellText);
            }
        }
        System.out.println("Test Stop");
    }

    @Then("Copy and Store the data into a database\\(Excel)")
    public void copy_and_store_the_data_into_a_database_excel() {
        System.out.println("Inside Given");
    }

    @Then("Save the file under folder structure")
    public void save_the_file_under_folder_structure() {
        System.out.println("Inside Given");
    }

    @Then("Validate the file size is not zero")
    public void validate_the_file_size_is_not_zero() {
        System.out.println("Inside Given");
    }

    @And("Navigate to About Page")
    public void navigate_to_about_page() {
        System.out.println("Inside Given");
    }

}