package eagel.tailung;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.security.Security;

@Path("/")
public class Resource {
    @Inject
    SecurityIdentity securityIdentity;
    @GET
    @RolesAllowed({"student","professor","admin"})
    @Path("course_list")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getCourseList(){
        return Response.ok("Course List: CS ME EE" + securityIdentity.getPrincipal().getName()).build();
    }
    @GET
    @RolesAllowed({"professor","admin"})
    @Path("add_course")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addCourse(){
        return Response.ok("Add Course: CE").build();
    }
    @GET
    @RolesAllowed("admin")
    @Path("update_course")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateCourse(){
        return Response.ok("Updated Course: CS -> CSE").build();
    }
    @GET
    @RolesAllowed("admin")
    @Path("delete_course")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteCourse(){
        return Response.ok("Deleted Coruse : ME").build();
    }


}
