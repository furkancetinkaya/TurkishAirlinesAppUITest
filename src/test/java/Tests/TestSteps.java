package Tests;

import Utils.JSONOperations;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestSteps extends TestBase{
    JSONOperations jsonOp = new JSONOperations();


    @Step("Click <elementKey> by ID")
    public void clickElementByID(String elementKey){
        String elementID = jsonOp.findElementByKey(elementKey);
        appiumDriver.findElement(By.id(elementID)).click();
        logger.info("Element: " + elementKey + " is clicked.");
    }

    @Step("Click <elementKey> by XPath")
    public void clickElementByXPath(String elementKey){
        MobileElement mobileElement = appiumDriver.findElement(By.xpath(jsonOp.findElementByKey(elementKey)));
        mobileElement.click();
        logger.info("Element: " + elementKey + " is clicked.");
    }

    @Step("Fill <text> into <elementKey> via ID")
    public void fillTextBoxViaID(String text, String elementKey){
        MobileElement mobileElement = appiumDriver.findElement(By.id(jsonOp.findElementByKey(elementKey)));
        mobileElement.clear();
        mobileElement.sendKeys(text);
        logger.info("Element: " + elementKey + " is filled with " + text + ".");
    }

    @Step("Fill <text> into <elementKey> via XPath")
    public void fillTextBoxViaXPath(String text, String elementKey){
        MobileElement mobileElement = appiumDriver.findElement(By.xpath(jsonOp.findElementByKey(elementKey)));
        mobileElement.clear();
        mobileElement.sendKeys(text);
        logger.info("Element: " + elementKey + " is filled with " + text + ".");
    }

    @Step("Assert <elementKey> via ID is present")
    public void assertElementViaIDPresent(String elementKey){
        MobileElement mobileElement = appiumDriver.findElement(By.id(jsonOp.findElementByKey(elementKey)));
        Assert.assertTrue("Flights are not listed!", mobileElement.isDisplayed());
        logger.info("Element: " + elementKey + " is asserted.");
    }

    @Step("Wait until <numSec> passed")
    public void waitUntilNumSec(int numSec){
        appiumDriver.manage().timeouts().implicitlyWait(numSec, TimeUnit.SECONDS);
        logger.info("Application waited " + numSec + " seconds.");
    }

    @Step("Select Departure Date (Current data + 2)")
    public void selectDepartureDate(){
        MobileElement mobileElement = appiumDriver.findElement(By.xpath(jsonOp.findElementByKey("DepartureDateWidget")));
        int currentDate = Integer.parseInt(mobileElement.getText()) + 2;
        mobileElement.click();
        String key = jsonOp.findElementByKey("DateDaySelection") + String.valueOf(currentDate) + "']";
        MobileElement dateSelection = appiumDriver.findElement(By.xpath(key));
        dateSelection.click();
        logger.info("Departure date is selected: " + currentDate);
    }

    @Step("Select and Click to <elementKey>")
    public void selectAndClick2Element(String elementKey){
        List<MobileElement> mobileElements = appiumDriver.findElements(By.xpath(jsonOp.findElementByKey(elementKey)));
        Random random = new Random();
        MobileElement mElement = mobileElements.get(random.nextInt(mobileElements.size()));
        mElement.click();
        logger.info("One of the flights is selected.");
    }

}
