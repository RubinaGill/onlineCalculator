package calculator.stepdefs;

import calculator.PageObjects.CalculatorPage;
import calculator.RunCucumberTests;
import io.cucumber.java.en.And;
import org.testng.log4testng.Logger;

public class Division extends RunCucumberTests {
    CalculatorPage calculatorPage;
    private final static Logger LOGGER = Logger.getLogger(CalculatorPage.class);

    @And("User divides by {string}")
    public void user_divides_by(String number) {
        try {
            calculatorPage=new CalculatorPage(getDriver());
            calculatorPage.selectOperator('/');
            for(char digit:number.toCharArray()) {
                calculatorPage.enterNumber(String.valueOf(digit));
            }
            calculatorPage.selectOperator('=');
        } catch (Exception e) {
            LOGGER.info("-----------------User is unable to enter number in calculator--------------------------" , e);
        }
    }
}
