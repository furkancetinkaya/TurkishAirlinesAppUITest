package Tests;

import Utils.JSONOperations;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestBase {
    protected Logger logger = LogManager.getLogger(getClass());
    protected static AndroidDriver<MobileElement> appiumDriver;

    @BeforeScenario
    public void setup(){
        logger.info("Setting up the Android Driver");

        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3000);
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "b891521b");
            caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.turkishairlines.mobile");
            caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.turkishairlines.mobile.ui.main.MainActivity");
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            appiumDriver = new AndroidDriver(url, caps);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        logger.info("Setting up the JSON Database");
        JSONOperations.jsonDB = new ArrayList<String>();
        JSONOperations.jsonDB.add("MainPage.json");
        JSONOperations.jsonDB.add("BookingPage.json");
        JSONOperations.jsonDB.add("SearchPage.json");
        JSONOperations.jsonDB.add("FlightsPage.json");
    }

    @AfterScenario
    public void exit(){
        if (appiumDriver != null)
            appiumDriver.quit();
    }
}
