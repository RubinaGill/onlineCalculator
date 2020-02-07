package calculator.PageObjects;

import calculator.Utilities.SeleniumUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

public class CalculatorPage extends SeleniumUtility {

    private final static Logger LOGGER = Logger.getLogger(CalculatorPage.class);
    private static By FULLFRAME = By.id("fullframe");
    private static By CANVAS = By.id("canvas");
    private static By OVERLAY_CONTAINER = By.id("dom_overlay_container");

    public CalculatorPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void NavigateToCanvas()throws Exception {
        try {
            switchToFrame(FULLFRAME);
            deleteElement(OVERLAY_CONTAINER);
        } catch (Exception e) {
            LOGGER.info("-----------------Exception occurred--------------------------" , e);
            throw new Exception("-----------------User is unable to navigate to canvas element--------------------------");
        }
    }

    public boolean enterNumber(String number) throws Exception{
        try {

            return moveWithinElement(CANVAS,(70)*getXPositionOfButton(number),(70)*getYPositionOfButton(number));
        } catch (Exception e) {
            LOGGER.info("-----------------Exception occurred--------------------------" , e);
            throw new Exception("-----------------User is unable to click on number--------------------------");
        }
    }

    private int getXPositionOfButton(String button) throws Exception{
        try {
            switch (button){
                case "1":
                case "0":
                case "4":
                case "7":
                    return 1;
                    case "8":
                case "5":
                case "2":
                case ".":
                    return 2;

                case "9":
                case "6":
                case "3":
                    return 3;
                case "-":
                case "/":
                        return 4;
                case "=":
                case "CE":
                case "C":
                        return 5;
            }
            return 0;
        } catch (Exception e) {
            LOGGER.info("-----------------Exception occurred--------------------------" , e);
            throw new Exception("-----------------User is unable to click on number--------------------------");
        }
    }


    private int getYPositionOfButton(String button) throws Exception{
        try {
            switch (button){
                case "CE":
                case "C":
                    return 2;
                case "7":
                case "8":
                case "9":
                case "/":
                    return 3;
                case "4":
                case "5":
                case "6":
                    return 4;
                case "3":
                case "-":
                case "1":
                case "2":
                    return 5;
                case "0":
                case ".":
                case "=":
                    return 6;
            }
            return 0;
        } catch (Exception e) {
            LOGGER.info("-----------------Exception occurred--------------------------" , e);
            throw new Exception("-----------------User is unable to click on number--------------------------");
        }
    }

    public boolean selectOperator(char operator)throws Exception {
        try {
            return moveWithinElement(CANVAS,(70)*getXPositionOfButton(String.valueOf(operator)),(70)*getYPositionOfButton(String.valueOf(operator)));
        } catch (Exception e) {
            LOGGER.info("-----------------Exception occurred--------------------------" , e);
            throw new Exception("-----------------User is unable to click on number--------------------------");
        }
    }

    public String fetchResult(){
        try {

            return getText(CANVAS,25,25,getElementWidth(CANVAS)-50,50);
        } catch (NumberFormatException e) {
            LOGGER.info("-----------------Exception occurred--------------------------" , e);
            return "";
        }
    }

    public String getButtonText(String buttonName){
        try {
            return getText(CANVAS,(70)*getXPositionOfButton(buttonName)-45,(70)*getYPositionOfButton(buttonName)-20,50,50);
        } catch (Exception e) {
            LOGGER.info("-----------------Exception occurred--------------------------" , e);
            return "";
        }
    }
}
