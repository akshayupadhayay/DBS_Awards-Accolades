package stepDefinition;

import base.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

public class SocialImpactSteps extends BaseClass {

    /**
     * Logger for info logs, WebDriverWait for elements to load
     */
    Logger logger = LogManager.getLogger(SocialImpactSteps.class.getName());
    WebDriverWait wait;

    /**
     * Page Factory with @CacheLookup for faster retrieval of WebElements
     */
    @CacheLookup @FindBy(id = "fullframe") WebElement calculatorFrame;
    @CacheLookup @FindBy(id = "canvas") WebElement calculatorCanvas;


    @Given("Browser is Open")
    public boolean browserIsOpen() {
        initDriver();
        PageFactory.initElements(driver, this);
        String defaultUrl = prop.getProperty("defaultUrl");
        try {
            logger.info("DBS default page is launched");
            driver.get(defaultUrl);
            return driver.getCurrentUrl().equals(prop.getProperty("defaultUrl"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @And("User is already on Sustainability Creating Social Impact page")
    public void user_is_already_on_sustainability_creating_social_impact_page() {
        System.out.println("Inside Given");
    }

    @When("Singapore F&B businesses data is available and displayed for user")
    public void singapore_f_b_businesses_data_is_available_and_displayed_for_user() {
        System.out.println("Inside Given");
    }

    @Then("Read & retrieve the table data from cells")
    public void read_retrieve_the_table_data_from_cells() {
        System.out.println("Inside Given");
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