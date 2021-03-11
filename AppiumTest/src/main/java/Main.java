import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        Test tester = new Test();
        tester.test1();

        LOGGER.log(Level.INFO, "TEST 1 IS COMPLETED");
    }

}