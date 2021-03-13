import io.appium.java_client.MobileElement;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {

    private static Driver driver;
    private final static Logger LOGGER = Logger.getLogger(Test.class.getName());

    public Test() {
        openApp();
        driver.waitForDriver();
    }

    /**
     * This method sets the Desired Capabilities and opens the app.
     * Please, make sure that you entered the correct parameters!
     */
    private void openApp() {

        try {
            driver = new Driver("sdk_gphone_x86",
                    "emulator-5554",
                    "Android",
                    "11",
                    "com.example.covid19_survey_app",
                    "com.example.covid19_survey_app.MainActivity",
                    "http://127.0.0.1:4723/wd/hub");
            LOGGER.log(Level.INFO, "APPLICATION OPENED");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Testing with correct inputs
    public boolean TestSuite1() {

        // Test with correct inputs with tarhanovac
        driver.editName("Dogukan");
        driver.editSurname("Kose");
        driver.editDate(01, 06, 1998);
        driver.editCity("Usak");
        driver.editGender(Driver.Gender.MALE);
        driver.editVaccineType(Driver.VaccineType.TARHANOVAC);
        driver.editSideEffect("Sweating");
        driver.clickSend();


        if (!driver.getDriver().findElementByXPath("//android.view.View[@content-desc=\"Bilgileriniz gönderildi!\"]")
                               .getTagName().equals("Bilgileriniz gönderildi!")) {
            LOGGER.warning("Testing with correct inputs with tarnanovac is failed!");
            return  false;
        }

        // Test with correct inputs with moderna
        driver.editName("Dogukan");
        driver.editSurname("Kose");
        driver.editDate(01, 06, 1998);
        driver.editCity("Usak");
        driver.editGender(Driver.Gender.MALE);
        driver.editVaccineType(Driver.VaccineType.MODERNA);
        driver.editSideEffect("Sweating");
        driver.clickSend();

        if (!driver.getDriver().findElementByXPath("//android.view.View[@content-desc=\"Your data is sent!\"]")
                               .getTagName().equals("Your data is sent!")) {
            LOGGER.warning("Testing with correct inputs with moderna is failed!");
            return  false;
        }

        // Test with correct inputs with pfizer
        driver.editName("Dogukan");
        driver.editSurname("Kose");
        driver.editDate(01, 06, 1998);
        driver.editCity("Usak");
        driver.editGender(Driver.Gender.MALE);
        driver.editVaccineType(Driver.VaccineType.PFIZER);
        driver.editSideEffect("Sweating");
        driver.clickSend();

        if (!driver.getDriver().findElementByXPath("//android.view.View[@content-desc=\"Ihre Daten werden gesendet!\"]")
                               .getTagName().equals("Ihre Daten werden gesendet!")) {
            LOGGER.warning("Testing with correct inputs with pfizer is failed!");
            return  false;
        }

        return true;
    }

    // Testing with Empty inputs
    public boolean TestSuite2() {

        return true;
    }

    // Testing
    public boolean TestSuite3() {

        return true;
    }

    // Testing
    public boolean TestSuite4() {

        return true;
    }

    // Testing
    public boolean TestSuite5() {

        return true;
    }

}
