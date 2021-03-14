import Exceptions.ElementNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

    private static Driver driver;
    private final static Logger LOGGER = LoggerFactory.getLogger(Test.class);

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
            LOGGER.info("APPLICATION OPENED");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Testing with correct inputs
    public boolean TestSuite1() {
        boolean isTestSuitePassed = true;

        // Test with correct inputs with tarhanovac
        try {
            driver.editName("Dogukan");
            driver.editSurname("Kose");
            driver.editDate(1, 6, 1998);
            driver.editCity("Usak");
            driver.editGender(Driver.Gender.MALE);
            driver.editVaccineType(Driver.VaccineType.TARHANOVAC);
            driver.editSideEffect("Sweating");
            driver.clickSend();

            if (!driver.getDriver().findElementByXPath("//android.view.View[@content-desc=\"Bilgileriniz gönderildi!\"]")
                    .getTagName().equals("Bilgileriniz gönderildi!")) {
                LOGGER.warn("Testing with correct inputs with tarhanovac is failed!");
                isTestSuitePassed = false;
            }
            LOGGER.info("Testing with correct inputs with tarhanovac is passed");
        } catch (ElementNotExistException ex) {
            ex.printStackTrace();
            isTestSuitePassed = false;
            driver.resetForm();
        }

        // Test with correct inputs with moderna
        try {
            driver.editName("Dogukan");
            driver.editSurname("Kose");
            driver.editDate(1, 6, 1998);
            driver.editCity("Usak");
            driver.editGender(Driver.Gender.FEMALE);
            driver.editVaccineType(Driver.VaccineType.MODERNA);
            driver.editSideEffect("Sweating");
            driver.clickSend();

            if (!driver.getDriver().findElementByXPath("//android.view.View[@content-desc=\"Your data is sent!\"]")
                    .getTagName().equals("Your data is sent!")) {
                LOGGER.warn("Testing with correct inputs with moderna is failed!");
                isTestSuitePassed = false;
            }
            LOGGER.info("Testing with correct inputs with moderna is passed");
        } catch (ElementNotExistException ex) {
            ex.printStackTrace();
            isTestSuitePassed = false;
            driver.resetForm();
        }

        // Test with correct inputs with pfizer
        try {
            driver.editName("Dogukan");
            driver.editSurname("Kose");
            driver.editDate(1, 6, 1998);
            driver.editCity("Usak");
            driver.editGender(Driver.Gender.MALE);
            driver.editVaccineType(Driver.VaccineType.PFIZER);
            driver.editSideEffect("Sweating");
            driver.clickSend();

            if (!driver.getDriver().findElementByXPath("//android.view.View[@content-desc=\"Ihre Daten werden gesendet!\"]")
                    .getTagName().equals("Ihre Daten werden gesendet!")) {
                LOGGER.warn("Testing with correct inputs with pfizer is failed!");
                isTestSuitePassed = false;
            }
            LOGGER.info("Testing with correct inputs with pfizer is passed");
        } catch (ElementNotExistException ex) {
            ex.printStackTrace();
            isTestSuitePassed = false;
            driver.resetForm();
        }

        return isTestSuitePassed;
    }

    // Testing with Empty inputs
    public boolean TestSuite2() {
        boolean isTestSuitePassed = true;

        // Only name empty
        try {
            driver.editSurname("temp surname");
            driver.editDate(3, 7, 1950);
            driver.editCity("Istanbul");
            driver.editGender(Driver.Gender.FEMALE);
            driver.editVaccineType(Driver.VaccineType.PFIZER);
            driver.editSideEffect("Nothing");

            if (driver.isSendButtonExist()) {
                isTestSuitePassed = false;
            }

            LOGGER.info("Testing with only empty name is passed!");
        } catch (ElementNotExistException ex) {
            ex.printStackTrace();
            isTestSuitePassed = false;
            driver.resetForm();
        }

        driver.resetForm();

        // Only surname empty
        try {
            driver.editName("temp name");
            driver.editDate(5, 15, 1960);
            driver.editCity("Istanbul");
            driver.editGender(Driver.Gender.FEMALE);
            driver.editVaccineType(Driver.VaccineType.PFIZER);
            driver.editSideEffect("Nothing");

            if (driver.isSendButtonExist()) {
                isTestSuitePassed = false;
            }

            LOGGER.info("Testing with only empty surname is passed!");
        } catch (ElementNotExistException ex) {
            ex.printStackTrace();
            isTestSuitePassed = false;
            driver.resetForm();
        }

        driver.resetForm();

        // Only date empty
        try {
            driver.editName("temp name");
            driver.editSurname("bahtiyar");
            driver.editCity("Ankara");
            driver.editGender(Driver.Gender.FEMALE);
            driver.editVaccineType(Driver.VaccineType.PFIZER);
            driver.editSideEffect("Nothing");

            if (driver.isSendButtonExist()) {
                isTestSuitePassed = false;
            }

            LOGGER.info("Testing with only empty date is passed!");
        } catch (ElementNotExistException ex) {
            ex.printStackTrace();
            isTestSuitePassed = false;
            driver.resetForm();
        }

        driver.resetForm();

        // Only city empty
        try {
            driver.editName("temp name");
            driver.editSurname("elmas");
            driver.editDate(5, 15, 1980);
            driver.editGender(Driver.Gender.MALE);
            driver.editVaccineType(Driver.VaccineType.MODERNA);
            driver.editSideEffect("Nothing");

            if (driver.isSendButtonExist()) {
                isTestSuitePassed = false;
            }

            LOGGER.info("Testing with only empty city is passed!");
        } catch (ElementNotExistException ex) {
            ex.printStackTrace();
            isTestSuitePassed = false;
            driver.resetForm();
        }

        driver.resetForm();

        // Only gender empty
        try {
            driver.editName("temp name");
            driver.editSurname("pehlivan");
            driver.editDate(5, 15, 2000);
            driver.editCity("Konya");
            driver.editVaccineType(Driver.VaccineType.TARHANOVAC);
            driver.editSideEffect("Nothing");

            if (driver.isSendButtonExist()) {
                isTestSuitePassed = false;
            }

            LOGGER.info("Testing with only empty gender is passed!");
        } catch (ElementNotExistException ex) {
            ex.printStackTrace();
            isTestSuitePassed = false;
            driver.resetForm();
        }

        driver.resetForm();

        // Only vaccine type empty
        try {
            driver.editName("temp name");
            driver.editSurname("bahtiyaroglu");
            driver.editDate(5, 15, 1960);
            driver.editCity("Izmir");
            driver.editGender(Driver.Gender.MALE);
            driver.editSideEffect("Nothing");

            if (driver.isSendButtonExist()) {
                isTestSuitePassed = false;
            }

            LOGGER.info("Testing with only empty vaccine type is passed!");
        } catch (ElementNotExistException ex) {
            ex.printStackTrace();
            isTestSuitePassed = false;
            driver.resetForm();
        }


        // There should not be send button !!!!

        return isTestSuitePassed;
    }

    // Testing with wrong date, wrong name wrong surname
    public boolean TestSuite3() {

        // Test with 1 character name

        // Test with 1 character surname

        // Test with numerical name

        // Test with numerical surname

        // Test with future date


        return true;
    }

    // Testing Clear Button
    public boolean TestSuite4() {

        // When clear button clicked all areas should be deleted

        return true;
    }

    // Testing
    public boolean TestSuite5() {

        return true;
    }

}
