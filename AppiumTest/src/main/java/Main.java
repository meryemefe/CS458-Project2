import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Test tester = new Test();

        if (tester.TestSuite1()) {
            LOGGER.info("TEST 1 IS COMPLETED");
        } else {
            LOGGER.info("TEST 1 IS FAILED");
        }


        // TestSuite2()
        tester.TestSuite2();

        // TestSuite3()
        tester.TestSuite3();

        // TestSuite4()
        tester.TestSuite4();

        // TestSuite5()
        tester.TestSuite5();

    }

}