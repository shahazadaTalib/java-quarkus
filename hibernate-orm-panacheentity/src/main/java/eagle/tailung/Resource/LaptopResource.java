package eagle.tailung.Resource;

import eagle.tailung.entity.Laptop;
import eagle.tailung.entity.LaptopRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Path("/laptop/")
public class LaptopResource {

    @Inject
    LaptopRepository laptopRepository;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLaptop(){
        List<Laptop> laptopList = laptopRepository.listAll();
        return Response.ok(laptopList).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getLaptopById(@PathParam("id") Long laptopId){
       Optional<Laptop> laptop = laptopRepository.findByIdOptional(laptopId);
       if(laptop.isPresent())
           return Response.ok(laptop.get()).build();
       return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLaptop(Laptop laptop){
        laptopRepository.persist(laptop);
        if(laptopRepository.isPersistent(laptop)){
            return Response.created(URI.create("/laptop/"+laptop.getId())).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @PUT
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateLaptop(@PathParam("id") Long laptopId,Laptop laptop){
        Optional<Laptop> savedLaptop=laptopRepository.findByIdOptional(laptopId);
        if(savedLaptop.isPresent()){
            Laptop svdlaptop= savedLaptop.get();
           if(laptop.getExternalStorage()!=0)
               svdlaptop.setExternalStorage(laptop.getExternalStorage());
           if(Objects.nonNull(laptop.getName()))
               svdlaptop.setName(laptop.getName());
           if(laptop.getRam()!=0)
               svdlaptop.setRam(laptop.getRam());
           if(Objects.nonNull(laptop.getBrand()))
               svdlaptop.setBrand(laptop.getBrand());
            laptopRepository.persist(svdlaptop);
            return Response.ok(URI.create("/laptop/"+laptopId)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deleteLaptop(@PathParam("id") Long laptopId ){
        Optional<Laptop> laptop = laptopRepository.findByIdOptional(laptopId);
        if(laptop.isPresent()){
            laptopRepository.deleteById(laptopId);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
