package eagle.tailung.repository;

import eagle.tailung.entity.Citizen;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CitizenRepo implements PanacheRepository<Citizen> {
}
