package eagel.tailung.repository;

import eagel.tailung.entity.Student;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentRepo implements PanacheRepository<Student> {
}
