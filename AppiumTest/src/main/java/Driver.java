import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    private AppiumDriver driver;

    public Driver( String deviceName,
                   String udid,
                   String platformName,
                   String platformVersion,
                   String appPackage,
                   String appActivity,
                   String urlAddress) throws Exception {

        DesiredCapabilities desiredCap = new DesiredCapabilities();
        desiredCap.setCapability("deviceName", deviceName);
        desiredCap.setCapability("udid", udid);
        desiredCap.setCapability("platformName", platformName);
        desiredCap.setCapability("platformVersion", platformVersion);
        desiredCap.setCapability("appPackage", appPackage);
        desiredCap.setCapability("appActivity", appActivity);

        URL url = new URL(urlAddress);
        driver = new AppiumDriver<MobileElement>(url, desiredCap);

    }

    public void waitForDriver(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

}
