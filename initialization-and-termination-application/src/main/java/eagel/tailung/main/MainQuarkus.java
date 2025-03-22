package eagel.tailung.main;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

public class MainQuarkus implements QuarkusApplication {

    @Override
    public int run(String... args) throws Exception {
        //write your logic
        System.out.println("..>>>application is starting....");
        Quarkus.waitForExit();
        return 0;
    }
}
