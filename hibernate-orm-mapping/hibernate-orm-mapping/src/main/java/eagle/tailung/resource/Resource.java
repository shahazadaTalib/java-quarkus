package eagle.tailung.resource;

import eagle.tailung.entity.Aadhar;
import eagle.tailung.entity.Citizen;
import eagle.tailung.entity.SimCard;
import eagle.tailung.repository.AadharRepo;
import eagle.tailung.repository.CitizenRepo;
import eagle.tailung.repository.SimCardRepo;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/citizenaadharsimcard/")
public class Resource {
    @Inject
    CitizenRepo citizenRepo;
    @Inject
    AadharRepo aadharRepo;
    @Inject
    SimCardRepo simCardRepo;
    @POST
    @Transactional
    @Path("save")
    public Response saveCitizen(){
        Citizen citizen = new Citizen();
        citizen.setGender("M");
        citizen.setName("KuchBhi");

        SimCard simCard = new SimCard();
        simCard.setActive(true);
        simCard.setNumber(8786L);
        simCard.setProvider("vi");
        simCard.setCitizen(citizen);

        SimCard simCard1 = new SimCard();
        simCard1.setActive(true);
        simCard1.setNumber(666L);
        simCard1.setProvider("vi");
        simCard1.setCitizen(citizen);

        SimCard simCard2 = new SimCard();
        simCard2.setActive(true);
        simCard2.setNumber(85556L);
        simCard2.setProvider("vi");
        simCard2.setCitizen(citizen);


        //Citizen citizen = citizenRepo.findById(1L);
        Aadhar aadhar = new Aadhar();
        aadhar.setAadharNumber(11L);
        aadhar.setCompany("UIDAI");
        aadhar.setCitizen(citizen);
        citizen.setSimCards(List.of(simCard,simCard1,simCard2));
        citizen.setAadhar(aadhar);

        citizenRepo.persist(citizen);
      //  aadharRepo.persist(aadhar);
        return Response.ok(citizen).build();
    }
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCitizenById(@PathParam("id")Long citizenId){
        Optional<Citizen> citizenOptional = citizenRepo.findByIdOptional(citizenId);
        if(citizenOptional.isPresent()){
            return Response.ok(citizenOptional.get()).build();
        }
        return Response.noContent().build();
    }
}
