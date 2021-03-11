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

    public void test1(){
        driver.editName("temp name");
        driver.editSurname("temp surname");
        driver.editCity("temp city");
        driver.editGender("temp gender");
        driver.editVaccineType("temp vaccine type");
        driver.editSideEffect("temp side effect");
        driver.clickSend();
    }

}
