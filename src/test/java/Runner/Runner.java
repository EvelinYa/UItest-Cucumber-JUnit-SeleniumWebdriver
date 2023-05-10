package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags ="@CriticalPath", features = "src/main/java/features", glue = {"stepDefinitions"}, monochrome = true)
public class Runner {
}