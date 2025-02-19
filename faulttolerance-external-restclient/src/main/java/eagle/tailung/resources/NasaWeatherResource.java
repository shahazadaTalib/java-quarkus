package eagle.tailung.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.Random;

@Path("/api")
public class NasaWeatherResource {
    public static final Logger LOGGER = Logger.getLogger(NasaWeatherResource.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("weather_by_country/{country}")
    public Response weatherByCountry(@PathParam("country") String country)  {
        LOGGER.info("Calling weatherByCountry in NasaWeather");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Response.ok("weather of "+country+" : "+new Random().nextInt(100)).build();
    }
}

