package eagel.tailung.resource;

import eagel.tailung.entity.Student;
import eagel.tailung.repository.StudentRepo;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.hibernate.tool.schema.internal.StandardUserDefinedTypeExporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class StudentResourceTest {
    @Inject
    StudentResource studentResource;
    @InjectMock
    StudentRepo studentRepo;
    Student student;
    @BeforeEach
    void setUp() {
        student = new Student();
        student.setName("Aakash");
        student.setBranch("CS");
    }
    @Test
    void createStudent() {
    }
    @Test
    void getAllStudent() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1L,"Shruti","CS"));
        studentList.add(new Student(2L,"Rahul","CS"));
        studentList.add(new Student(3L,"Aakansha","ME"));
        Mockito.when(studentRepo.listAll()).thenReturn(studentList);
        Response response =  studentResource.getAllStudent();
        assertNotNull(response);
        assertNotNull(response.getEntity());
        assertEquals(response.getStatus(),Response.Status.OK.getStatusCode());
        List<Student> studentList1 = (List<Student>)response.getEntity();
        assertEquals(studentList.size(),3);
    }
    @Test
    void getCSStudentList() {
    }
    @Test
    void getStudentById() {
        Student student1 = new Student(4L,"Rajesh","ME");
        Mockito.when(studentRepo.findById(ArgumentMatchers.any(Long.class))).thenReturn(student1);
        Response response = studentResource.getStudentById(4L);
        assertNotNull(response);
        assertNotNull(response.getEntity());
        assertEquals(response.getStatus(),Response.Status.OK.getStatusCode());
        Student student2 = (Student) response.getEntity();
        assertEquals(student2.getBranch(),"ME");
        assertEquals(student2.getName(),"Rajesh");
    }
    @Test
    void getStudentByIdKO(){
        Mockito.when(studentRepo.findById(ArgumentMatchers.any(Long.class))).thenReturn(null);
        Response response = studentResource.getStudentById(5L);
        assertNotNull(response);
        assertEquals(response.getStatus(),Response.Status.NO_CONTENT.getStatusCode());
    }
    @Test
    void updateStudent() {
    }
    @Test
    void deleteStudentById() {
        Mockito.when(studentRepo.deleteById(ArgumentMatchers.any(Long.class))).thenReturn(true);
        Response response = studentResource.deleteStudentById(3L);
        assertNotNull(response);
        boolean isDeleted =(boolean)response.getEntity();
        assertEquals(isDeleted,true);
        assertEquals(response.getStatus(),Response.Status.NO_CONTENT);
    }

    @Test void deleteStudentByIdKO(){
        Mockito.when(studentRepo.deleteById(ArgumentMatchers.any(Long.class))).thenReturn(false);
        Response response = studentResource.deleteStudentById(5l);
        assertNotNull(response);
        boolean isDeleted = (boolean)response.getEntity();
        assertEquals(isDeleted,false);
        assertEquals(response.getStatus(),Response.Status.NOT_FOUND);

    }
    @Test
    void getStudentByBranch() {
    }
}