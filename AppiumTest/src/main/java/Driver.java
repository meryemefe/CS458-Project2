import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
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

    public void editName( String name){
        MobileElement edittextName = (MobileElement) driver.findElementsByClassName("android.widget.EditText").get(0);
        edittextName.click();
        driver.getKeyboard().sendKeys(name);
    }

    public void editSurname( String surname){
        MobileElement edittextSurname = (MobileElement) driver.findElementsByClassName("android.widget.EditText").get(1);
        edittextSurname.click();
        driver.getKeyboard().sendKeys(surname);
    }

    public void editCity( String city){
        MobileElement edittextCity = (MobileElement) driver.findElementsByClassName("android.widget.EditText").get(2);
        edittextCity.click();
        driver.getKeyboard().sendKeys(city);
    }

    public void editGender( String gender){
        MobileElement edittextGender = (MobileElement) driver.findElementsByClassName("android.widget.EditText").get(3);
        edittextGender.click();
        driver.getKeyboard().sendKeys(gender);
    }

    public void editVaccineType( String vaccineType){
        MobileElement edittextVaccineType = (MobileElement) driver.findElementsByClassName("android.widget.EditText").get(4);
        edittextVaccineType.click();
        driver.getKeyboard().sendKeys(vaccineType);
    }

    public void editSideEffect( String sideEffect){
        MobileElement edittextSurname = (MobileElement) driver.findElementsByClassName("android.widget.EditText").get(5);
        edittextSurname.click();
        driver.getKeyboard().sendKeys(sideEffect);
    }

    public void clickSend(){
        MobileElement buttonSend = (MobileElement) driver.findElement(By.className("android.widget.Button"));
        buttonSend.click();
    }

}
