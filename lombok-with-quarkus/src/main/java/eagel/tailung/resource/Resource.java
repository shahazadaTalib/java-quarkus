package eagel.tailung.resource;

import eagel.tailung.entity.Aadhar;
import eagel.tailung.entity.Citizen;
import eagel.tailung.repository.AadharRepo;
import eagel.tailung.repository.CitizenRepo;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/")
public class Resource {
    @Inject
    CitizenRepo citizenRepo;
    @Inject
    AadharRepo aadharRepo;

    @GET
    @Path("get")
    @Transactional
    public Response saveCitizen(){
        Aadhar aadhar = new Aadhar();
        aadhar.setAadharNumber(2222L);
        aadhar.setCompany("UF");

        Citizen citizen = new Citizen();
        citizen.setGender("Male");
        citizen.setName("joe");
        aadhar.setCitizen(citizen);
        citizen.setAadhar(aadhar);

        citizenRepo.persist(citizen);
        aadharRepo.persist(aadhar);
        return Response.ok("Saved").build();
    }
}
