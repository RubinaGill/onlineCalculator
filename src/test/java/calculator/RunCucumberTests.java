package calculator;

import calculator.Utilities.PropertyManager;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.log4testng.Logger;

import java.lang.reflect.Method;

@CucumberOptions(features = {"src/test/resources/calculator/features"},
        glue = {"calculator"},
        plugin = {"html:target/cucumber-html-report","json:target/cucumber-report.json"})
public class RunCucumberTests extends AbstractTestNGCucumberTests {
    final static Logger logger = Logger.getLogger(RunCucumberTests.class);

    String browser = PropertyManager.getInstance().getProperty("Browser");
    String path = System.getProperty("user.dir");
    protected String baseURL = PropertyManager.getInstance().getProperty("AppUrl");

    protected static ThreadLocal<WebDriver> webDriver = new ThreadLocal();

    @BeforeMethod
    public void CreateDriver(Method testMethod) {
        try {
            switch (browser) {
                case "chrome": {
                    String completeChromePath = path + "\\src\\test\\Resources\\calculator\\chromedriver_win32\\chromedriver.exe";
                    System.setProperty("webdriver.chrome.driver", completeChromePath);
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("disable-extensions");
                    chromeOptions.addArguments("--disable-infobars");
                    chromeOptions.addArguments("start-maximized");
                    chromeOptions.addArguments("no-sandbox");
                    chromeOptions.setExperimentalOption("useAutomationExtension", false);
                    webDriver.set(new ChromeDriver(chromeOptions));
                    getDriver().manage().window().setSize(new Dimension(1382,744));
                    break;
                }
                case "IE":

                    break;
                default:
                    throw new Exception("browser is not available:" + browser);
            }

            getDriver().navigate().to( baseURL);
            logger.info("------------------------------------Starting Execution of-------------------------------" + testMethod.getName());
        } catch (Exception e) {
            logger.info("Error occurred-------------------------------" + e);
            e.printStackTrace();
        }
    }

    public WebDriver getDriver() {
        //Get driver from ThreadLocalMap
        return webDriver.get();
    }

    @AfterMethod
    public void quitDriver(ITestResult result) {
        logger.info("-------------------closing driver------------------------------------------ Test case:  "+logTestResult(result));
        getDriver().quit();
    }
    private String logTestResult(ITestResult result){
        switch(result.getStatus()){
            case 1:
                return "Passed";
            case 2:
                return "Failed";
            case 3:
                return "Skipped";
            default:
                return "Success_Percentage_Failure";
        }
    }
}
