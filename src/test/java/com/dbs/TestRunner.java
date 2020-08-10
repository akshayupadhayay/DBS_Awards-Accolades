package com.dbs;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.dbs.stepDefinitions"},
        plugin = {"de.monochromata.cucumber.report.PrettyReports:target/HTMLReports",
                  "json:target/JSONReports/report.json"},
        monochrome = false
        )
public class TestRunner {

}
