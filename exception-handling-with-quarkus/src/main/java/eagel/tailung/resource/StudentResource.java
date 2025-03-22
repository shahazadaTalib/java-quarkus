package eagel.tailung.resource;

import eagel.tailung.entity.Student;
import eagel.tailung.exception.BussinessException;
import eagel.tailung.exception.TechnicalException;
import eagel.tailung.repository.StudentRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.net.URI;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentResource {
    @Inject
    StudentRepository studentRepository;

    @POST
    @Path("addStudent")
    @Transactional
    public Response addStudent(@RequestBody Student student){
        if(student==null || student.getName()==null || student.getName().length()==0)
            try {
                throw  new BussinessException(Response.Status.BAD_REQUEST.getStatusCode(), "Student name cannot be empty");
            } catch (BussinessException e) {
                throw new RuntimeException(e);
            }
        studentRepository.persist(student);
        if(studentRepository.isPersistent(student)){
            return Response.created(URI.create("/student/"+student.getStudentId())).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @GET
    @Path("student/{id}")
    public Response getStudentById(@PathParam("id") Long id) throws BussinessException {
        Student student = studentRepository.findById(id);
        if(student == null)
            //return Response.ok(Response.status(Response.Status.NOT_FOUND)).build();
            throw new BussinessException(Response.Status.NOT_FOUND.getStatusCode(),"Student with id : "+id+" not found in DB");
        return Response.ok(student).build();
    }
    @GET
    @Path("getAllStudent")
    public Response getStudent(){
        return Response.ok(studentRepository.listAll()).build();
    }
    @GET
    @Path("test_api/{i}")
    public  Response testApi(@PathParam("i") int i) throws TechnicalException {
        try{
            int output=8/i;
            return Response.ok("output after dividing "+output).build();
        }catch (Exception e){
            throw new TechnicalException(Response.Status.BAD_REQUEST.getStatusCode(), "Cannot divide a number by "+i);
        }
    }
}
