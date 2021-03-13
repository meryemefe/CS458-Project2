import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        Test tester = new Test();

        if (tester.TestSuite1()) {
            LOGGER.log(Level.INFO, "TEST 1 IS COMPLETED");
        } else {
            LOGGER.log(Level.INFO, "TEST 1 IS FAILED");
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