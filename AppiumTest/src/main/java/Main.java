import Exceptions.ElementNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        //Run android test
        Test tester = new Test();

        //Run ios test
        //TestIOS tester = new TestIOS();

        if (tester.TestSuite1()) {
            LOGGER.info("TEST SUITE 1 IS COMPLETED");
        } else {
            LOGGER.info("TEST SUITE 1 IS FAILED");
        }

        // TestSuite2()
        if (tester.TestSuite2()) {
            LOGGER.info("TEST SUITE 2 COMPLETED");
        } else {
            LOGGER.info("TEST SUITE 2 IS FAILED");
        }

        // TestSuite3()
        if (tester.TestSuite3()) {
            LOGGER.info("TEST SUITE 3 COMPLETED");
        } else {
            LOGGER.info("TEST SUITE 3 IS FAILED");
        }

        // TestSuite4()
        if (tester.TestSuite4()){
            LOGGER.info("TEST SUITE 4 COMPLETED");
        } else {
            LOGGER.info("TEST SUITE 4 IS FAILED");
        }

        // TestSuite5()
        if (tester.TestSuite5()){
            LOGGER.info("TEST SUITE 5 COMPLETED");
        } else {
            LOGGER.info("TEST SUITE 5 IS FAILED");
        }
    }

}