package eagel.tailung.main;

import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Startup
public class CarService {
    @PostConstruct
    public void startEngine(){
        System.out.println("starting the engine.");
    }
    @PreDestroy
    public void stopEngine(){
        System.out.println("stopping the engine.");
    }
}
