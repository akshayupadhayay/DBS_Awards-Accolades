package stepDefinition;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinition"},
        plugin = {"de.monochromata.cucumber.report.PrettyReports:target/HTMLReports",
                  "json:target/JSONReports/report.json"},
        monochrome = false,
        tags = "@SocialImpactTest"
        )
public class TestRunner {

}
