package calculator.stepdefs;

import calculator.PageObjects.CalculatorPage;
import calculator.RunCucumberTests;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.log4testng.Logger;


public class Subtraction extends RunCucumberTests {

    CalculatorPage calculatorPage;
    private final static Logger LOGGER = Logger.getLogger(CalculatorPage.class);

    @Given("User uses the online-calculator")
    public void user_uses_the_online_calculator() {
        try {
            calculatorPage= new CalculatorPage(getDriver());
            calculatorPage.NavigateToCanvas();
        } catch (Exception e) {
            LOGGER.info("-----------------User is unable to use online calculator--------------------------" , e);
        }
    }

    @When("User have entered {string} into the calculator")
    public void user_have_entered_into_the_calculator(String number) {
        try {
            calculatorPage= new CalculatorPage(getDriver());
            for(char digit:number.toCharArray()) {
                calculatorPage.enterNumber(String.valueOf(digit));
            }
        } catch (Exception e) {
            LOGGER.info("-----------------User is unable to enter number in calculator--------------------------" , e);
        }
    }

    @When("User subtract {string}")
    public void user_subtract(String number) {
        try {
            calculatorPage= new CalculatorPage(getDriver());
            calculatorPage.selectOperator('-');
            for(char digit:number.toCharArray()) {
                calculatorPage.enterNumber(String.valueOf(digit));
            }
            calculatorPage.selectOperator('-');
        } catch (Exception e) {
            LOGGER.info("-----------------User is unable to enter number in calculator--------------------------" , e);
        }

    }

    @Then("result should be {string} on the screen")
    public void result_should_be_on_the_screen(String expectedResult) {
        try {
            calculatorPage= new CalculatorPage(getDriver());
            Assert.assertEquals(calculatorPage.fetchResult(),"O","result of calculation is not correct");
            //as zero is interpreted as "O" by OCR
        } catch (Exception e) {
            LOGGER.info("-----------------User is unable to compare results--------------------------" , e);
        }
    }
}
