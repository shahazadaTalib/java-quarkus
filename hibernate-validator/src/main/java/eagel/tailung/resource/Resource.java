package eagel.tailung.resource;

import eagel.tailung.entity.Citizen;
import eagel.tailung.repository.CitizenRepo;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Set;
import java.util.stream.Collectors;


@Path("/")
public class Resource {
    @Inject
    CitizenRepo citizenRepo;
    @Inject
    Validator validator;

    @POST
    @Path("save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response saveCitizen(Citizen citizen){
        Set<ConstraintViolation<Citizen>> validate = validator.validate(citizen);
        if(!validate.isEmpty()){
            String errorString = validate.stream().map(v -> v.getMessage()).collect(Collectors.joining(", "));
            return Response.status(Response.Status.BAD_REQUEST).entity(errorString).build();
        }
        citizenRepo.persist(citizen);
        if(citizenRepo.isPersistent(citizen))
            return Response.ok(citizen).build();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @POST
    @Path("save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response saveCitizen2(@Valid Citizen citizen){
        citizenRepo.persist(citizen);
        if(citizenRepo.isPersistent(citizen))
            return Response.ok(citizen).build();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
