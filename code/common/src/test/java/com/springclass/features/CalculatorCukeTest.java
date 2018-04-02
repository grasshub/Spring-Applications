package com.springclass.features;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;


@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(CucumberWithSerenity.class)
//@RunWith(CucumberWithSerenity.class)

@CucumberOptions(//features="src/test/resources/stories/Calculator.feature",
        format = { "pretty", "html:target/cuke", "json:target/cucumber.json" },
        features = {"src/test/resources/com/springclass/features/Calculator.feature"},
        glue = "com.springclass.features")
public class CalculatorCukeTest {
}
