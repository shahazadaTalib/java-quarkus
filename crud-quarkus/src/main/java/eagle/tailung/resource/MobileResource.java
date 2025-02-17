package eagle.tailung.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/mobile/")
public class MobileResource {
    List<Mobile> mobileList = new ArrayList<>();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMobileList(){
        return Response.ok(mobileList).build();
    }
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMobileById(@PathParam("id") int id){
       Optional<Mobile> mob =  mobileList.stream().filter(mobile -> mobile.getId()==id).findFirst();
       if(mob.isPresent()){
           return Response.ok(mob.get()).build();
       }
       return Response.status(Response.Status.NOT_FOUND).build();

    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMobile(Mobile mobile){
        mobileList.add(mobile);
        return Response.ok(mobile).build();
    }
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMobile(@PathParam("id") int id, Mobile mobile){
        mobileList=mobileList.stream().map(mob->{
            if(mob.id==id){
                return  mobile;
            }
            return mob;
        }).collect(Collectors.toList());
        return Response.ok(mobile).build();
    }
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMobile(@PathParam("id")int id){

        //mobileList = mobileList.stream().filter(mob->mob.id==id).collect(Collectors.toList());
        Optional<Mobile> mobileToDelete = mobileList.stream().filter(mob->mob.getId()==id)
                .findFirst();
        if(mobileToDelete.isPresent()){
            mobileList.remove(mobileToDelete.get());
            return Response.ok(mobileList).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
