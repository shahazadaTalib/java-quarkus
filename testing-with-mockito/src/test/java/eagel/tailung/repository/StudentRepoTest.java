package eagel.tailung.repository;

import eagel.tailung.entity.Student;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.listeners.StubbingLookupEvent;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class StudentRepoTest {
    @Inject
    StudentRepo studentRepo;
    @Test
    void listAll(){
       List<Student> studentList = studentRepo.listAll();
       assertFalse(studentList.isEmpty());
       assertEquals(studentList.size(),4);
       assertEquals(studentList.get(0).getName(),"Rahul");

    }
    @Test
    void findById(){
       Student student = studentRepo.findById(4L);
       assertNotNull(student);
       assertEquals(student.getStudentId(),4L);
       assertEquals(student.getName(),"Aayush");
       assertEquals(student.getBranch(),"CE");
    }
    @Test
    void getStudentListByBranch(){
        List<Student> studentList = studentRepo.getStudentListByBranch("EE");
        assertFalse(studentList.isEmpty());
        assertEquals(studentList.size(),1);
    }







}