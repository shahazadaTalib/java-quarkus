package eagel.tailung.repository;

import eagel.tailung.entity.Student;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class StudentRepo implements PanacheRepository<Student> {
    public List<Student> getStudentListByBranch(String branch){
        return list("Select s from Student s where s.branch=?1",branch);
    }
}
