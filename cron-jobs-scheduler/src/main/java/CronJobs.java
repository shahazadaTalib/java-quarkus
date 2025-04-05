import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CronJobs {
    @Scheduled(every = "10s")
    public void executeEvery10s(){
        System.out.println("cron job me");
    }
    @Scheduled(cron = "0 41 15 * * ?")
    public void executeEveryday(){
        System.out.println("cron job with time and date ");
    }
}
