package stepdefs;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features ="classpath:hellocucumber/SameCardRule.feature",glue = {"stepdefs"})
public class TestRunner {
}
