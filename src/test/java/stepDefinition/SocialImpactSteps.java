package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SocialImpactSteps {

    @Given("User is already on Sustainability Creating Social Impact page")
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