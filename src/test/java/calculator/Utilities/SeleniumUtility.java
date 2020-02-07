package calculator.Utilities;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;
import com.asprise.ocr.Ocr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SeleniumUtility {
    private int timeOutInSeconds = 60;

    final static Logger LOGGER = Logger.getLogger(SeleniumUtility.class);

    public SeleniumUtility(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, timeOutInSeconds);
    }

    private WebDriverWait wait;
    private WebDriver webDriver;

    protected int getElementWidth(By webElementLocator){
        return  wait.until(ExpectedConditions.elementToBeClickable(webElementLocator)).getSize().width;
    }
    protected boolean moveWithinElement(By webElementLocator, int xOffset, int yOffset) throws Exception {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webElementLocator));
            new Actions(webDriver).moveToElement(element, 0, 0)
                    .moveByOffset(xOffset, yOffset)
                    .click()
                    .build()
                    .perform();
            return true;
        } catch (Exception e) {
            LOGGER.info("-----------------Exception occurred--------------------------", e);
            throw new Exception("user not able to move within element By offset ");
        }
    }


    protected boolean switchToFrame(By frameElement) throws Exception {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
            return true;
        } catch (Exception e) {
            LOGGER.info("-----------------Exception occurred--------------------------", e);
            throw new Exception("user is not able to switch frame");
        }
    }

    protected boolean deleteElement(By frameElement) throws Exception {
        try {
            ((JavascriptExecutor) webDriver).executeScript("return document.getElementById('dom_overlay_container').remove();");
            return true;
        } catch (Exception e) {
            LOGGER.info("-----------------Exception occurred--------------------------", e);
            throw new Exception("user is not able to switch frame");
        }
    }

    protected String getText(By webElementLocator, int xOffset, int yOffset, int width, int height) {
        // Get entire page screenshot
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webElementLocator));
        File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        String screenshotName="temp_screenshot.png";
        try {
            String path = System.getProperty("user.dir");
            BufferedImage fullImg = ImageIO.read(screenshot);
            Point point = element.getLocation();

            BufferedImage eleScreenshot = fullImg.getSubimage(point.getX() + xOffset, point.getY() + yOffset,
                    width, height);
            ImageIO.write(eleScreenshot, "png", screenshot);

            // Copy the element screenshot to disk
            File screenshotLocation = new File(path+"\\src\\test\\resources\\"+screenshotName);
            FileUtils.copyFile(screenshot, screenshotLocation);
            System.setProperty("TESSDATA_PREFIX", path+"\\tessData");
            ITesseract tesseract = new Tesseract();

            //tesseract.setTessVariable("tessedit_char_whitelist", "0123456789");

            tesseract.setDatapath(path+"\\tessData");
            File imageFile = new File(path + "\\src\\test\\resources\\"+screenshotName);
            String result = tesseract.doOCR(imageFile);
            return result.trim();
        }catch (TesseractException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
