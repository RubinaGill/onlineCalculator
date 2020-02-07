package calculator.stepdefs;

import calculator.PageObjects.CalculatorPage;
import calculator.RunCucumberTests;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import org.testng.log4testng.Logger;

public class CE extends RunCucumberTests {
   CalculatorPage calculatorPage;
    private final static Logger LOGGER = Logger.getLogger(CalculatorPage.class);

    @Then("{string} button must be visible")
    public void button_must_be_visible(String expectedResult) {
        try {
            calculatorPage=new CalculatorPage(getDriver());
            Assert.assertEquals(expectedResult,calculatorPage.getButtonText("CE"),"user is unable to view CE button");
        } catch (Exception e) {
            LOGGER.info("-----------------User is unable to compare results--------------------------" , e);
            Assert.fail("--------Exception occurred-----------------");
        }
    }
    
  @Then("{string} should change to {string}")
    public void should_change_to(String button1,String button2) {
        try {
            calculatorPage=new CalculatorPage(getDriver());
            Assert.assertEquals(button2,calculatorPage.getButtonText(button1),"User is unable to view C button");
        } catch (Exception e) {
            LOGGER.info("-----------------User is unable to compare results--------------------------" , e);
            Assert.fail("--------Exception occurred-----------------");
        }
    }
}
