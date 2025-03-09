package eagel.tailung.resource;

import eagel.tailung.entity.Student;
import eagel.tailung.repository.StudentRepo;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentResource {
    @Inject
    StudentRepo studentRepo;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getStudentList")
    public Response getStudentList(){
        List<Student> studentList = studentRepo.listAll();
        return Response.ok(studentList).build();
    }
    @GET
    @Path("csstudent/{branch}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentListByBranch(@QueryParam("branch") String branch){
        List<Student> studentList = studentRepo.list("branch=?1",branch);
        return Response.ok(studentList).build();
    }
    @GET
    @Path("csstudent")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCSStudentList(){
        List<Student> csstudentList = new ArrayList<>();
        List<Student> studentList = studentRepo.listAll();
        studentList.forEach(s->{
            if(s.getBranch().equalsIgnoreCase("CS")){
                csstudentList.add(s);
            }
        });
        return Response.ok(csstudentList).build();
    }
    @POST
    @Path("addStudent")
    @Transactional
    public Response addStudent(@RequestBody Student student){
        studentRepo.persist(student);
        if(studentRepo.isPersistent(student)){
            return Response.created(URI.create("/student/"+student.getStudentId())).build();
        }
        return Response.ok(Response.Status.BAD_REQUEST).build();
    }
    @GET
    @Path("student/{studentId}")
    public Response getStudentByStudentId(@PathParam("studentId") Long studentId){
        Student student = studentRepo.findById(studentId);
        if(student==null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(student).build();
    }

    @GET
    @Path("getAllstudentTest")
    public Response getTestStudentList(){
       List<Student> students = studentRepo.listAll();
        return Response.ok(students).build();
    }
}
