package eagel.tailung.resource;

import eagel.tailung.entity.Student;
import eagel.tailung.repository.StudentRepo;
import io.vertx.core.metrics.Measured;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.annotations.Param;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
@Path("/student/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {
    @Inject
    StudentRepo studentRepo;
    @POST
    @Path("createStudent")
    public Response createStudent(@RequestBody Student student){
        studentRepo.persist(student);
        if(studentRepo.isPersistent(student))
            return Response.created(URI.create("/student/"+student.getStudentId())).build();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @GET
    @Path("getStudents")
    public Response getAllStudent(){
        List<Student> studentList = studentRepo.listAll();
        return Response.ok(studentList).build();
    }
    @GET
    @Path("getCSStudent")
    public Response getCSStudentList(){
        List<Student> csStudentList = new ArrayList<>();
        List<Student> studentList = studentRepo.listAll();
        studentList.forEach(s->{
            if(s.getBranch().equalsIgnoreCase("CS")){
                csStudentList.add(s);
            }
        });
        return Response.ok(csStudentList).build();
    }
    @GET
    @Path("branch/{branch}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentByBranch(@PathParam("branch") String branch){
        List<Student> studentList = studentRepo.list("branch",branch);
        return Response.ok(studentList).build();
    }
    @GET
    @Path("student/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getStudentById(@PathParam("id") Long id){
        Student student = studentRepo.findById(id);
        if(student!=null)
            return  Response.ok(student).build();
        return Response.status((Response.Status.NO_CONTENT)).build();
    }
    @PUT
    @Path("updateStudent/{id}")
    public Response updateStudent(@RequestBody Student student,@PathParam("id") Long id){
        Student savedStudent = studentRepo.findById(id);
        if(savedStudent!=null){
            savedStudent.setName(student.getName());
            return Response.ok(student).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @POST
    @Path("delete/{id}")
    public Response deleteStudentById(@PathParam("id") Long id){
        boolean isDeleted = studentRepo.deleteById(id);
        if(isDeleted)
            return Response.status(Response.Status.NO_CONTENT).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
