import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static Driver driver;
    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        // Open the app
        openApp();
        driver.waitForDriver();

    }

    /**
     * This method sets the Desired Capabilities and opens the app.
     * Please, make sure that you entered the correct parameters!
     */
    public static void openApp() {

        try {
            driver = new Driver("sdk_gphone_x86",
                    "emulator-5554",
                    "Android",
                    "11",
                    "com.example.covid_survey_app",
                    "com.example.covid_survey_app.MainActivity",
                    "http://127.0.0.1:4723/wd/hub");
            LOGGER.log(Level.INFO, "APPLICATION OPENED");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}