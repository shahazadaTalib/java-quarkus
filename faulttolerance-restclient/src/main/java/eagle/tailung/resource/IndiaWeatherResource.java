package eagle.tailung.resource;

import eagle.tailung.restclient.NasaWeatherRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class IndiaWeatherResource {
    @RestClient
    NasaWeatherRestClient weatherRestClient;

    Logger LOGGER = Logger.getLogger(IndiaWeatherResource.class);


    @GET
    @Path("weather/{country}")
    @Fallback(fallbackMethod = "getWeatherByCountryFallback")
    @Retry(maxRetries = 3)
    @Timeout(2000)
    @CircuitBreaker(requestVolumeThreshold = 4,failureRatio = 0.5,delay = 1000)
    public Response getWeatherByCountry(@PathParam("country") String country){
        LOGGER.info("calling get weather by Country");
        Long startTime = System.currentTimeMillis();
        Response response = weatherRestClient.weatherByCountry(country);
        Long endTime = System.currentTimeMillis();
        String resp = response.readEntity(String.class);
        resp = resp + " >>> " +( (endTime-startTime) /100);
        return Response.ok(resp).build();
    }
    public Response getWeatherByCountryFallback(String country){
        return Response.ok("Server is down").build();
    }
}
