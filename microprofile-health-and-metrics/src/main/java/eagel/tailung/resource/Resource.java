package eagel.tailung.resource;

import eagel.tailung.restclient.JsonPlaceHolderRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/")
public class Resource {
    @RestClient
    JsonPlaceHolderRestClient restClient;
    private long highestPrimeNumberSoFar=2;
    @GET
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello(){
        return Response.ok("Hello from Quarkus").build();
    }

    @GET
    @Path("/{number}")
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(
            name = "count_of_checkIfPrime",
            description = "number of time method/api called"
    )
    @Timed(
            name = "Time_taken_checkIfPrime",
            description = "calculate time take to respond",
            unit = MetricUnits.MILLISECONDS

    )
    @Metered(
        name = "Metered_checkIfPrime",
        description = "How frequent this is called."
    )
    public String checkIfPrime(@PathParam("number") long number){
        if(number<1){
            return "Only natural numbers can be prime numbers.";
        }
        if(number==1){
            return "1 is not prime.";
        }
        if(number==2){
            return "2 is prime.";
        }
        if(number%2==0){
            return number+" is not prime, It is divisible by 2.";
        }
        for(int i=3;i<Math.floor(Math.sqrt(number))+1;i=i+2){
            if(number%i==0){
                return number + " is not prime, is divisible by "+ i +",";
            }
        }
        if(number>highestPrimeNumberSoFar){
            highestPrimeNumberSoFar=number;
        }
        return number + " is prime.";
    }
    @Gauge(
        name = "highest prime number request",
        description = "what is the highest prime number calculated so far",
        unit = MetricUnits.NONE
    )
    public Long getHighestPrimeNumber(){
        return highestPrimeNumberSoFar;
    }

}
