import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    private final static Logger LOGGER = LoggerFactory.getLogger(Driver.class);

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

    public AppiumDriver getDriver() {
        return driver;
    }

    public void waitForDriver(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void editName( String name){
        MobileElement editTextName = (MobileElement) driver.findElementsByClassName("android.widget.EditText").get(0);
        editTextName.click();
        driver.getKeyboard().sendKeys(name);
    }

    public void editSurname(String surname){
        MobileElement editTextSurname = (MobileElement) driver.findElementsByClassName("android.widget.EditText").get(1);
        editTextSurname.click();
        driver.getKeyboard().sendKeys(surname);
    }

    public void editDate(int month, int day, int year) {
        MobileElement dateButton = (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc=\"Birth Date\"]");
        dateButton.click();
        MobileElement editDateButton = (MobileElement) driver.findElementByXPath("//android.widget.Button[@content-desc=\"Switch to input\"]");
        editDateButton.click();
        sleep(500L);
        driver.getKeyboard().sendKeys(month + "/" + day + "/" + year);
        MobileElement okTextButton = (MobileElement) driver.findElementByXPath("//android.widget.Button[@content-desc=\"OK\"]");
        okTextButton.click();
    }

    public void editCity(String city){
        sleep(500L);
        MobileElement editTextCity = (MobileElement) driver.findElementsByClassName("android.widget.EditText").get(2);
        editTextCity.click();
        driver.getKeyboard().sendKeys(city);
    }

    public void editGender(Gender gender){
        MobileElement editTextGender = (MobileElement) driver.findElementByXPath("//android.widget.Button[@content-desc=\"Please select a gender\"]");
        editTextGender.click();
        if (gender.equals(Gender.MALE)) {
            MobileElement maleButton = (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc=\"Male\"]");
            maleButton.click();
        } else {
            MobileElement femaleButton = (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc=\"Female\"]");
            femaleButton.click();
        }
    }

    public void editVaccineType(VaccineType vaccineType){
        MobileElement editTextVaccineType = (MobileElement) driver.findElementByXPath("//android.widget.Button[@content-desc=\"Please select a vaccine\"]");
        editTextVaccineType.click();
        String vaccineCountryName = null;
        if (vaccineType.equals(VaccineType.CORONOVAC)) {
            vaccineCountryName = "China-Coronovac";
        } else if (vaccineType.equals(VaccineType.PFIZER)) {
            vaccineCountryName = "Germany-Pfizer";
        } else if (vaccineType.equals(VaccineType.MODERNA)) {
            vaccineCountryName = "USA-Moderna";
        } else if (vaccineType.equals(VaccineType.TARHANOVAC)) {
            vaccineCountryName = "Turkey-Tarhanovac";
        }
        if (vaccineCountryName != null) {
            MobileElement option = (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc=\"" + vaccineCountryName + "\"]");
            option.click();
        }
    }

    public void editSideEffect(String sideEffect){
        sleep(500L);
        MobileElement editSideEffect = (MobileElement) driver.findElementsByClassName("android.widget.EditText").get(3);
        editSideEffect.click();
        driver.getKeyboard().sendKeys(sideEffect);
    }

    public void clickSend(){
        driver.hideKeyboard();
        MobileElement buttonSend = (MobileElement) driver.findElementByXPath("//android.widget.Button[@content-desc=\"Send\"]");
        buttonSend.click();
    }

    public enum Gender {
        MALE, FEMALE
    }

    public enum VaccineType {
        PFIZER, CORONOVAC, TARHANOVAC, MODERNA
    }

    private void sleep(Long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


