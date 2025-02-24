package eagle.tailung.repository;

import eagle.tailung.entity.Bank;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BankRepo implements PanacheRepository<Bank> {
}
