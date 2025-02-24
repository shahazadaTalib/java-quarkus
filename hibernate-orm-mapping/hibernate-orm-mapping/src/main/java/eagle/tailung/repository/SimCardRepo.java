package eagle.tailung.repository;

import eagle.tailung.entity.SimCard;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SimCardRepo implements PanacheRepository<SimCard> {
}
