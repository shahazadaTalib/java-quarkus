package eagle.tailung.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
@Path("/shows/")
@RegisterRestClient(baseUri = "https://api.tvmaze.com")
public interface TvSeriesIdProxy {
    @GET
    @Path("{id}")
    TvSeries getTvSeriesById(@PathParam("id") int id);
    //we can make here multiple method if there base url is same for all method
    //you can extend url further over the function
}
