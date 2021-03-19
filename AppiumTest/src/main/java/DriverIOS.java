import Exceptions.ElementNotExistException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverIOS {

    private final static Logger LOGGER = LoggerFactory.getLogger(Driver.class);

    private AppiumDriver driver;

    private static By pickers = MobileBy.className("XCUIElementTypePickerWheel");

    public DriverIOS( String deviceName,
                   String udid,
                   String platformName,
                   String platformVersion,
                   String automationName,
                   String app,
                   String urlAddress) throws Exception {

        DesiredCapabilities desiredCap = new DesiredCapabilities();
        desiredCap.setCapability("deviceName", deviceName);
        desiredCap.setCapability("udid", udid);
        desiredCap.setCapability("platformName", platformName);
        desiredCap.setCapability("platformVersion", platformVersion);
        desiredCap.setCapability("automationName", automationName);
        desiredCap.setCapability("app", app);

        URL url = new URL(urlAddress);
        driver = new AppiumDriver<MobileElement>(url, desiredCap);

    }

    public AppiumDriver getDriver() {
        return driver;
    }

    public void waitForDriver(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public String getName() throws ElementNotExistException {
        sleep(100L);
        String name = "";
        try {
            MobileElement editTextName = (MobileElement) driver.findElementByAccessibilityId("Name");
            name = editTextName.getText();
        }catch (Exception ex) {
            throw new ElementNotExistException("Name Input Box");
        }

        return name;
    }

    public String getSurname() throws ElementNotExistException{
        sleep(100L);
        String surname = "";
        try {
            MobileElement editTextSurname = (MobileElement) driver.findElementByAccessibilityId("Surname");
            surname = editTextSurname.getText();
        } catch (Exception ex) {
            throw new ElementNotExistException("Surname Input Box");
        }
        return surname;
    }

    /*
    public String getDate() throws ElementNotExistException {
        sleep(100L);
        String date = "";
        try {
            MobileElement dateButton = (MobileElement) driver.findElementByAccessibilityId("Birth Date");
            date = dateButton.getAttribute("content-desc");
        } catch (Exception ex) {
            throw new ElementNotExistException("Date Box");
        }
        return date;
    } */

    public String getCity() throws ElementNotExistException{
        sleep(100L);
        String city = "";
        try {
            MobileElement editTextCity = (MobileElement) driver.findElementByAccessibilityId("City");
            city = editTextCity.getText();
        } catch (Exception ex) {
            throw new ElementNotExistException("City Input Box");
        }
        return city;
    }

    public String getGender() throws ElementNotExistException {
        sleep(100L);
        String gender = "";
        try {
            MobileElement editTextGender = (MobileElement) driver.findElementByAccessibilityId("Please select a gender");
            gender = editTextGender.getText();
        } catch (Exception ex) {
            throw  new ElementNotExistException("Gender Dropdown");
        }
        return gender;
    }

    public String getVaccineType() throws ElementNotExistException {
        sleep(100L);
        String vaccine = "";
        try {
            MobileElement editTextVaccineType = (MobileElement) driver.findElementByAccessibilityId("Please select a vaccine");
            vaccine = editTextVaccineType.getText();
        } catch (Exception ex) {
            throw new ElementNotExistException("Vaccine Dropdown");
        }

        return vaccine;
    }

    public String getSideEffect() throws ElementNotExistException {
        sleep(100L);
        String sideEffect = "";
        try {
            MobileElement editSideEffect = (MobileElement) driver.findElementByAccessibilityId("Side effect after vaccination");
            sideEffect = editSideEffect.getText();
        } catch (Exception ex) {
            throw new ElementNotExistException("Side Effect Input Box");
        }
        return sideEffect;
    }

    public void editName( String name) throws ElementNotExistException {
        sleep(100L);
        try {
            MobileElement editTextName = (MobileElement) driver.findElementByAccessibilityId("Name");
            editTextName.click();
            driver.getKeyboard().sendKeys(name);
        } catch (Exception ex) {
            throw new ElementNotExistException("Name Input Box");
        }
    }

    public void editSurname(String surname) throws ElementNotExistException {
        sleep(100L);
        try {
            MobileElement editTextSurname = (MobileElement) driver.findElementByAccessibilityId("Surname");
            editTextSurname.click();
            driver.getKeyboard().sendKeys(surname);
        } catch (Exception ex) {
            throw new ElementNotExistException("Surname Input Box");
        }

    }

    public void editDate(String direction) throws ElementNotExistException {
        sleep(100L);
        try {
            MobileElement dateButton = (MobileElement) driver.findElementByAccessibilityId("Birth Date");
            dateButton.click();

            sleep(100L);

            MobileElement pickerEl1 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"covid19_survey_app\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]");
            MobileElement pickerEl2 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"covid19_survey_app\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]");
            MobileElement pickerEl3 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"covid19_survey_app\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[4]");

            final long ANIMATION_TIME = 100L; // ms

            final HashMap<String, String> scrollObject = new HashMap();

            scrollObject.put("direction", direction);
            scrollObject.put("element", pickerEl1.getId());
            driver.executeScript("mobile: swipe", scrollObject);
            sleep(ANIMATION_TIME);

            scrollObject.put("element", pickerEl2.getId());
            driver.executeScript("mobile: swipe", scrollObject);
            sleep(ANIMATION_TIME);

            scrollObject.put("element", pickerEl3.getId());
            driver.executeScript("mobile: swipe", scrollObject);
            sleep(ANIMATION_TIME);

            MobileElement empty = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"covid19_survey_app\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]");
            empty.click();
        } catch (Exception ex) {
            throw new ElementNotExistException("Date Input Box");
        }

    }

    public void editCity(String city) throws ElementNotExistException {
        sleep(100L);
        try {
            MobileElement editTextCity = (MobileElement) driver.findElementByAccessibilityId("City");
            editTextCity.click();
            driver.getKeyboard().sendKeys(city);
        } catch (Exception ex) {
            throw new ElementNotExistException("City Input Box");
        }
    }

    public void editGender(Gender gender) throws ElementNotExistException {
        sleep(100L);
        try {
            MobileElement editTextGender = (MobileElement) driver.findElementByAccessibilityId("Please select a gender");
            editTextGender.click();
            if (gender.equals(Gender.MALE)) {
                MobileElement maleButton = (MobileElement) driver.findElementByAccessibilityId("Male");
                maleButton.click();
            } else {
                MobileElement femaleButton = (MobileElement) driver.findElementByAccessibilityId("Female");
                femaleButton.click();
            }
        } catch (Exception ex) {
            throw  new ElementNotExistException("Gender Dropdown");
        }
    }

    public void editVaccineType(VaccineType vaccineType) throws ElementNotExistException {
        sleep(100L);
        try {
            MobileElement editTextVaccineType = (MobileElement) driver.findElementByAccessibilityId("Please select a vaccine");
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
                MobileElement option = (MobileElement) driver.findElementByAccessibilityId(vaccineCountryName);
                option.click();
            }
        } catch (Exception ex) {
            throw new ElementNotExistException("Vaccine Dropdown");
        }
    }

    public void editSideEffect(String sideEffect) throws ElementNotExistException {
        sleep(100L);
        try {
            MobileElement editSideEffect = (MobileElement) driver.findElementByAccessibilityId("Side effect after vaccination");
            editSideEffect.click();
            driver.getKeyboard().sendKeys(sideEffect);
        } catch (Exception ex) {
            throw new ElementNotExistException("Side Effect Input Box");
        }

    }

    public void clickSend() throws ElementNotExistException {
        sleep(200L);
        try {
            driver.hideKeyboard();
            MobileElement buttonSend = (MobileElement) driver.findElementByAccessibilityId("Send");
            buttonSend.click();
        } catch (Exception ex) {
            throw new ElementNotExistException("Send Button");
        }
    }


    public void deleteName(String name) throws ElementNotExistException {
        sleep(100L);
        try {
            MobileElement editTextName = (MobileElement) driver.findElementByAccessibilityId("Name");
            editTextName.click();
            editTextName.clear();
            driver.hideKeyboard();
        } catch (Exception ex) {
            throw new ElementNotExistException("Name Input");
        }
    }

    public void resetForm() {
        sleep(200L);
        driver.hideKeyboard();
        MobileElement buttonReset = (MobileElement) driver.findElementByAccessibilityId("Clear");
        buttonReset.click();
    }

    public boolean isSendButtonExist() {
        try {
            driver.hideKeyboard();
            MobileElement buttonSend = (MobileElement) driver.findElementByAccessibilityId("Send");
            if (buttonSend.isDisplayed() || buttonSend.isEnabled()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
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


