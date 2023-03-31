package intelliShift.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {
                "html:target/cucumber-report.html",
                "json:target/cucumber.json"
        },
        features = "src/test/resources/features",
        glue = "intelliShift/step_definitions",
        dryRun = false,
        tags = "@wip")

public class CukesRunner extends AbstractTestNGCucumberTests {
}
