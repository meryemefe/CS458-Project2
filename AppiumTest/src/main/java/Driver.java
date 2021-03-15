import Exceptions.ElementNotExistException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
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

    public String getName() throws ElementNotExistException {
        sleep(100L);
        String name = "";
        try {
            MobileElement editTextName = (MobileElement) driver.findElementsByClassName("android.widget.EditText").get(0);
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
            MobileElement editTextSurname = (MobileElement) driver.findElementsByClassName("android.widget.EditText").get(1);
            surname = editTextSurname.getText();
        } catch (Exception ex) {
            throw new ElementNotExistException("Surname Input Box");
        }
        return surname;
    }

    public String getDate() throws ElementNotExistException {
        sleep(100L);
        String date = "";
        try {
            MobileElement dateButton = (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc=\"Birth Date\"]");
            date = dateButton.getAttribute("content-desc");
        } catch (Exception ex) {
            throw new ElementNotExistException("Date Box");
        }
        return date;
    }

    public String getCity() throws ElementNotExistException{
        sleep(100L);
        String city = "";
        try {
            MobileElement editTextCity = (MobileElement) driver.findElementsByClassName("android.widget.EditText").get(2);
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
            MobileElement editTextGender = (MobileElement) driver.findElementByXPath("//android.widget.Button[@content-desc=\"Please select a gender\"]");
            gender = editTextGender.getAttribute("content-desc");
        } catch (Exception ex) {
            throw  new ElementNotExistException("Gender Dropdown");
        }
        return gender;
    }

    public String getVaccineType() throws ElementNotExistException {
        sleep(100L);
        String vaccine = "";
        try {
            MobileElement editTextVaccineType = (MobileElement) driver.findElementByXPath("//android.widget.Button[@content-desc=\"Please select a vaccine\"]");
            vaccine = editTextVaccineType.getAttribute("content-desc");
        } catch (Exception ex) {
            throw new ElementNotExistException("Vaccine Dropdown");
        }

        return vaccine;
    }

    public String getSideEffect() throws ElementNotExistException {
        sleep(100L);
        String sideEffect = "";
        try {
            MobileElement editSideEffect = (MobileElement) driver.findElementsByClassName("android.widget.EditText").get(3);
            sideEffect = editSideEffect.getText();
        } catch (Exception ex) {
            throw new ElementNotExistException("Side Effect Input Box");
        }
        return sideEffect;
    }

    public void editName( String name) throws ElementNotExistException {
        sleep(100L);
        try {
            MobileElement editTextName = (MobileElement) driver.findElementsByClassName("android.widget.EditText").get(0);
            editTextName.click();
            driver.getKeyboard().sendKeys(name);
        } catch (Exception ex) {
            throw new ElementNotExistException("Name Input Box");
        }
    }

    public void editSurname(String surname) throws ElementNotExistException {
        sleep(100L);
        try {
            MobileElement editTextSurname = (MobileElement) driver.findElementsByClassName("android.widget.EditText").get(1);
            editTextSurname.click();
            driver.getKeyboard().sendKeys(surname);
        } catch (Exception ex) {
            throw new ElementNotExistException("Surname Input Box");
        }

    }

    public void editDate(int month, int day, int year) throws ElementNotExistException {
        sleep(100L);
        try {
            MobileElement dateButton = (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc=\"Birth Date\"]");
            dateButton.click();
            MobileElement editDateButton = (MobileElement) driver.findElementByXPath("//android.widget.Button[@content-desc=\"Switch to input\"]");
            editDateButton.click();
            sleep(250L);
            driver.getKeyboard().sendKeys(month + "/" + day + "/" + year);
            MobileElement okTextButton = (MobileElement) driver.findElementByXPath("//android.widget.Button[@content-desc=\"OK\"]");
            okTextButton.click();
        } catch (Exception ex) {
            throw new ElementNotExistException("Date Box");
        }

    }

    public void editCity(String city) throws ElementNotExistException {
        sleep(100L);
        try {
            MobileElement editTextCity = (MobileElement) driver.findElementsByClassName("android.widget.EditText").get(2);
            editTextCity.click();
            driver.getKeyboard().sendKeys(city);
        } catch (Exception ex) {
            throw new ElementNotExistException("City Input Box");
        }
    }

    public void editGender(Gender gender) throws ElementNotExistException {
        sleep(100L);
        try {
            MobileElement editTextGender = (MobileElement) driver.findElementByXPath("//android.widget.Button[@content-desc=\"Please select a gender\"]");
            editTextGender.click();
            if (gender.equals(Gender.MALE)) {
                MobileElement maleButton = (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc=\"Male\"]");
                maleButton.click();
            } else {
                MobileElement femaleButton = (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc=\"Female\"]");
                femaleButton.click();
            }
        } catch (Exception ex) {
            throw  new ElementNotExistException("Gender Dropdown");
        }
    }

    public void editVaccineType(VaccineType vaccineType) throws ElementNotExistException {
        sleep(100L);
        try {
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
        } catch (Exception ex) {
            throw new ElementNotExistException("Vaccine Dropdown");
        }
    }

    public void editSideEffect(String sideEffect) throws ElementNotExistException {
        sleep(100L);
        try {
            MobileElement editSideEffect = (MobileElement) driver.findElementsByClassName("android.widget.EditText").get(3);
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
            MobileElement buttonSend = (MobileElement) driver.findElementByXPath("//android.widget.Button[@content-desc=\"Send\"]");
            buttonSend.click();
        } catch (Exception ex) {
            throw new ElementNotExistException("Send Button");
        }
    }

    public void deleteName(String name) throws ElementNotExistException {
        sleep(100L);
        try {
            MobileElement editTextName = (MobileElement) driver.findElementsByClassName("android.widget.EditText").get(0);
            editTextName.click();
            for (int i = 0; i < name.length(); i++)
                driver.getKeyboard().sendKeys(Keys.BACK_SPACE);
        } catch (Exception ex) {
            throw new ElementNotExistException("Name Input");
        }
    }

    public void resetForm() {
        sleep(200L);
        driver.hideKeyboard();
        MobileElement buttonReset = (MobileElement) driver.findElementByXPath("//android.widget.Button[@content-desc=\"Clear\"]");
        buttonReset.click();
    }

    public boolean isSendButtonExist() {
        try {
            driver.hideKeyboard();
            MobileElement buttonSend = (MobileElement) driver.findElementByXPath("//android.widget.Button[@content-desc=\"Send\"]");
            if (buttonSend.isDisplayed()) {
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


