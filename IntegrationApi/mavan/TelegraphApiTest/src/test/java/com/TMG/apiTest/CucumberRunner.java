package com.TMG.apiTest;

        import com.TMG.apiTest.config.AppConfig;
        import cucumber.api.CucumberOptions;
        import cucumber.api.junit.Cucumber;
        import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        tags = {"@api"},
        features = {"classpath:com/TMG/apiTest/featureFile"},
        glue = {"classpath:com/TMG/apiTest/stepDefinitions"}
)
public class CucumberRunner {

}
