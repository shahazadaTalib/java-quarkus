package eagel.tailung.main;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;

public class CarFunction {
    public void setRace(@Observes StartupEvent startupEvent){
        System.out.println("Car is running.");
    }
    public void setBreak(@Observes StartupEvent startupEvent){
        System.out.println("Applying the Break.");
    }
}
