import Exceptions.ElementNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestIOS {

  private static DriverIOS driver;
  private final static Logger LOGGER = LoggerFactory.getLogger(Test.class);

  public TestIOS() {
    //openApp();
    //driver.waitForDriver();

    openApp();
    driver.waitForDriver();
  }

  /**
   * This method sets the Desired Capabilities and opens the app.
   * Please, make sure that you entered the correct parameters!
   */
  private void openApp() {
    try {
      driver = new DriverIOS("iPhone",
                                "7FD9584E-8CD7-47E3-888C-AF7431A4F62C",
                                "IOS",
                                "14.0",
                                "XCUITest",
                                "/Users/admin/workspace/CS458-Project2/MobileApp/build/ios/Debug-iphonesimulator/Runner.app",
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
      driver.editDate("down");
      driver.editCity("Usak");
      driver.editGender(DriverIOS.Gender.MALE);
      driver.editVaccineType(DriverIOS.VaccineType.TARHANOVAC);
      driver.editSideEffect("Sweating");
      driver.clickSend();

      if (!driver.getDriver().findElementByAccessibilityId("Bilgileriniz gönderildi!")
                 .getText().equals("Bilgileriniz gönderildi!")) {
        LOGGER.warn("Testing with correct inputs with tarhanovac is failed!");
        isTestSuitePassed = false;
      } else {
        LOGGER.info("Testing with correct inputs with tarhanovac is passed");
      }
    } catch (ElementNotExistException ex) {
      ex.printStackTrace();
      isTestSuitePassed = false;
      driver.resetForm();
    }

    // Test with correct inputs with moderna
    try {
      driver.editName("Dogukan");
      driver.editSurname("Kose");
      driver.editDate("down");
      driver.editCity("Usak");
      driver.editGender(DriverIOS.Gender.FEMALE);
      driver.editVaccineType(DriverIOS.VaccineType.MODERNA);
      driver.editSideEffect("Sweating");
      driver.clickSend();

      if (!driver.getDriver().findElementByAccessibilityId("Your data is sent!")
                 .getText().equals("Your data is sent!")) {
        LOGGER.warn("Testing with correct inputs with moderna is failed!");
        isTestSuitePassed = false;
      } else {
        LOGGER.info("Testing with correct inputs with moderna is passed");
      }

    } catch (ElementNotExistException ex) {
      ex.printStackTrace();
      isTestSuitePassed = false;
      driver.resetForm();
    }

    // Test with correct inputs with pfizer
    try {
      driver.editName("Dogukan");
      driver.editSurname("Kose");
      driver.editDate("down");
      driver.editCity("Usak");
      driver.editGender(DriverIOS.Gender.MALE);
      driver.editVaccineType(DriverIOS.VaccineType.PFIZER);
      driver.editSideEffect("Sweating");
      driver.clickSend();

      if (!driver.getDriver().findElementByAccessibilityId("Ihre Daten werden gesendet!")
                 .getText().equals("Ihre Daten werden gesendet!")) {
        LOGGER.warn("Testing with correct inputs with pfizer is failed!");
        isTestSuitePassed = false;
      } else {
        LOGGER.info("Testing with correct inputs with pfizer is passed");
      }

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
      driver.editDate("down");
      driver.editCity("Istanbul");
      driver.editGender(DriverIOS.Gender.FEMALE);
      driver.editVaccineType(DriverIOS.VaccineType.PFIZER);
      driver.editSideEffect("Nothing");

      if (driver.isSendButtonExist()) {
        isTestSuitePassed = false;
        LOGGER.info("Testing with only empty name is failed!");
      } else {
        LOGGER.info("Testing with only empty name is passed!");
      }
    } catch (ElementNotExistException ex) {
      ex.printStackTrace();
      isTestSuitePassed = false;
      driver.resetForm();
    }

    driver.resetForm();

    // Only surname empty
    try {
      driver.editName("temp name");
      driver.editDate("down");
      driver.editCity("Istanbul");
      driver.editGender(DriverIOS.Gender.FEMALE);
      driver.editVaccineType(DriverIOS.VaccineType.PFIZER);
      driver.editSideEffect("Nothing");

      if (driver.isSendButtonExist()) {
        isTestSuitePassed = false;
        LOGGER.info("Testing with only empty surname is failed!");
      } else {
        LOGGER.info("Testing with only empty surname is passed!");
      }
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
      driver.editGender(DriverIOS.Gender.FEMALE);
      driver.editVaccineType(DriverIOS.VaccineType.PFIZER);
      driver.editSideEffect("Nothing");

      if (driver.isSendButtonExist()) {
        isTestSuitePassed = false;
        LOGGER.info("Testing with only empty date is failed!");
      } else {
        LOGGER.info("Testing with only empty date is passed!");
      }


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
      driver.editDate("down");
      driver.editGender(DriverIOS.Gender.MALE);
      driver.editVaccineType(DriverIOS.VaccineType.MODERNA);
      driver.editSideEffect("Nothing");

      if (driver.isSendButtonExist()) {
        isTestSuitePassed = false;
        LOGGER.info("Testing with only empty city is failed!");
      } else {
        LOGGER.info("Testing with only empty city is passed!");
      }
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
      driver.editDate("down");
      driver.editCity("Konya");
      driver.editVaccineType(DriverIOS.VaccineType.TARHANOVAC);
      driver.editSideEffect("Nothing");

      if (driver.isSendButtonExist()) {
        isTestSuitePassed = false;
        LOGGER.info("Testing with only empty gender is failed!");
      } else {
        LOGGER.info("Testing with only empty gender is passed!");
      }
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
      driver.editDate("down");
      driver.editCity("Izmir");
      driver.editGender(DriverIOS.Gender.MALE);
      driver.editSideEffect("Nothing");

      if (driver.isSendButtonExist()) {
        isTestSuitePassed = false;
        LOGGER.info("Testing with only empty vaccine type is failed!");
      } else {
        LOGGER.info("Testing with only empty vaccine type is passed!");
      }

    } catch (ElementNotExistException ex) {
      ex.printStackTrace();
      isTestSuitePassed = false;
      driver.resetForm();
    }

    driver.resetForm();

    // There should not be send button !!!!

    return isTestSuitePassed;
  }

  // Testing with wrong date, wrong name wrong surname
  public boolean TestSuite3() {
    boolean isTestSuitePassed = true;

    // Test with 1 character name
    try {
      driver.editName("t");
      driver.editSurname("kosele");
      driver.editDate("down");
      driver.editCity("Samsun");
      driver.editGender(DriverIOS.Gender.FEMALE);
      driver.editVaccineType(DriverIOS.VaccineType.TARHANOVAC);
      driver.editSideEffect("something is flying in my stomach");

      if (driver.isSendButtonExist()) {
        isTestSuitePassed = false;
        LOGGER.info("Testing with one character name is failed!");
      } else {
        LOGGER.info("Testing with one character name is passed!");
      }
    } catch (ElementNotExistException ex) {
      ex.printStackTrace();
      isTestSuitePassed = false;
      driver.resetForm();
    }

    driver.resetForm();

    // Test with 1 character surname
    try {
      driver.editName("jackson");
      driver.editSurname("m");
      driver.editDate("down");
      driver.editCity("Antalya");
      driver.editGender(DriverIOS.Gender.MALE);
      driver.editVaccineType(DriverIOS.VaccineType.MODERNA);
      driver.editSideEffect("meh");

      if (driver.isSendButtonExist()) {
        isTestSuitePassed = false;
        LOGGER.info("Testing with one character surname is failed!");
      } else {
        LOGGER.info("Testing with one character surname is passed!");
      }
    } catch (ElementNotExistException ex) {
      ex.printStackTrace();
      isTestSuitePassed = false;
      driver.resetForm();
    }

    driver.resetForm();

    // Test with numerical name
    try {
      driver.editName("t12354");
      driver.editSurname("myNameIsNumber");
      driver.editDate("down");
      driver.editCity("Nigde");
      driver.editGender(DriverIOS.Gender.MALE);
      driver.editVaccineType(DriverIOS.VaccineType.PFIZER);
      driver.editSideEffect("just kidding");

      if (driver.isSendButtonExist()) {
        isTestSuitePassed = false;
        LOGGER.info("Testing with numerical name is failed!");
      } else {
        LOGGER.info("Testing with numerical name is passed!");
      }
    } catch (ElementNotExistException ex) {
      ex.printStackTrace();
      isTestSuitePassed = false;
      driver.resetForm();
    }

    driver.resetForm();

    // Test with numerical surname
    try {
      driver.editName("mySurnameIsNumber");
      driver.editSurname("4233453");
      driver.editDate("down");
      driver.editCity("Aksaray");
      driver.editGender(DriverIOS.Gender.FEMALE);
      driver.editVaccineType(DriverIOS.VaccineType.CORONOVAC);
      driver.editSideEffect("just kidding 2");

      if (driver.isSendButtonExist()) {
        isTestSuitePassed = false;
        LOGGER.info("Testing with numerical surname is failed!");
      } else {
        LOGGER.info("Testing with numerical surname is passed!");
      }
    } catch (ElementNotExistException ex) {
      ex.printStackTrace();
      isTestSuitePassed = false;
      driver.resetForm();
    }

    driver.resetForm();

    // Test with future date
    try {
      driver.editName("temp name");
      driver.editSurname("temp surname");
      driver.editDate("up");
      driver.editCity("Aksaray");
      driver.editGender(DriverIOS.Gender.FEMALE);
      driver.editVaccineType(DriverIOS.VaccineType.TARHANOVAC);
      driver.editSideEffect("I will be born in future");

      if (driver.isSendButtonExist()) {
        isTestSuitePassed = false;
        LOGGER.info("Testing with future born date is failed!");
      } else {
        LOGGER.info("Testing with future born date is passed!");
      }
    } catch (ElementNotExistException ex) {
      ex.printStackTrace();
      isTestSuitePassed = false;
      driver.resetForm();
    }

    driver.resetForm();

    return isTestSuitePassed;
  }

  // Testing
  public boolean TestSuite4() {
    boolean isTestSuitePassed=true;
    // When clear button clicked all areas should be deleted
    // Fill with random input
    try {
      driver.editName("Hamza");
      driver.editSurname("Pehlivan");
      driver.editDate("down");
      driver.editCity("Konya");
      driver.editGender(DriverIOS.Gender.MALE);
      driver.editVaccineType(DriverIOS.VaccineType.TARHANOVAC);
      driver.editSideEffect("None");

      driver.resetForm();

      String name = driver.getName();
      String surname = driver.getSurname();
      String city = driver.getCity();
      String gender = driver.getGender();
      String vaccine = driver.getVaccineType();
      String sideEffect = driver.getSideEffect();

      if (name.equals("Name") && surname.equals("Surname") &&
          city.equals("City") &&
          gender.equals("Please select a gender") &&
          vaccine.equals("Please select a vaccine") &&
          sideEffect.equals("Side effect after vaccination")
      )
      {
        LOGGER.info("Reset button clears all inputs test is passed!");
      }
      else {
        isTestSuitePassed = false;
        LOGGER.info("Reset button clears all inputs test is failed!");
      }
    } catch (ElementNotExistException ex) {
      ex.printStackTrace();
      isTestSuitePassed = false;
      driver.resetForm();
    }

    return isTestSuitePassed;
  }

  // Testing visibility of send button and changing input after an input entered
  public boolean TestSuite5() {

    boolean isTestSuitePassed = true;

    try {
      driver.editName("dorukan");
      driver.editSurname("temp");
      driver.editDate("down");
      driver.editCity("Osmaniye");
      driver.editGender(DriverIOS.Gender.MALE);
      driver.editVaccineType(DriverIOS.VaccineType.TARHANOVAC);
      driver.editSideEffect("None");

      //check whether send button is visible
      if (!(driver.isSendButtonExist())) {
        isTestSuitePassed = false;
        LOGGER.info("Testing visibility of send button is failed!");
      } else {
        driver.deleteName("dorukan");

        //check whether send button is invisible
        if (driver.isSendButtonExist()) {
          isTestSuitePassed = false;
          LOGGER.info("Testing visibility of send button is failed!");
        } else {
          driver.editName("Dogukan");
          if (!driver.isSendButtonExist()) {
            isTestSuitePassed = false;
            LOGGER.info("Testing visibility of send button is failed!");
          } else {
            LOGGER.info("Testing visibility of send button is passed!");
          }
        }
      }

    } catch (ElementNotExistException ex) {
      ex.printStackTrace();
      isTestSuitePassed = false;
      driver.resetForm();
    }

    return isTestSuitePassed;
  }

}
