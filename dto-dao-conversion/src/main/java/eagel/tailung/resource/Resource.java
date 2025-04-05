package eagel.tailung.resource;

import eagel.tailung.dto.CitizenDTO;
import eagel.tailung.entity.Citizen;
import eagel.tailung.mapper.CitizenMapper;
import eagel.tailung.repository.CitizenRepo;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.net.URI;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Resource {
    @Inject
    CitizenRepo citizenRepo;

    @Inject
    CitizenMapper citizenMapper;

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addCitizen(@RequestBody CitizenDTO citizenDTO){
        Citizen citizen = citizenMapper.toDAO(citizenDTO);
        System.out.println(citizen.toString());
        return Response.ok(citizen).build();
//        citizenRepo.persist(citizen);
//        if(citizenRepo.isPersistent(citizen))
//            return Response.created(URI.create("/citizen/"+citizen.getId())).build();
//        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @GET
    @Path("citizen/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional//show changes in DB
    public Response getCitizenById(@PathParam("id") Long id){
        Citizen citizen = citizenRepo.findById(id);
        CitizenDTO citizenDTO = null;
        if(citizen!=null)
           citizenDTO = citizenMapper.toDTO(citizen);
            // citizen.setFirstName("Mr."+citizen.getFirstName());//reflect in DB
       return citizen!=null?Response.ok(citizenDTO).build():Response.status(Response.Status.NOT_FOUND).build();
    }
    @PUT
    @Path("citizen/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateCitizen(@PathParam("id") Long id,@RequestBody Citizen citizen){
        Citizen dbCitizen = citizenRepo.findById(id);
        if(dbCitizen!=null){
            if(dbCitizen.getAddress()!=null)
                dbCitizen.setAddress(citizen.getAddress());
            if(dbCitizen.getGender()!=null)
                dbCitizen.setGender(citizen.getGender());
            if(dbCitizen.getFirstName()!=null)
                dbCitizen.setFirstName(citizen.getFirstName());
            if(dbCitizen.getLastName()!=null)
                dbCitizen.setLastName(citizen.getLastName());
            return Response.ok(dbCitizen).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
