package eagle.tailung.resources;

import io.vertx.core.json.JsonArray;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/search/")
@RegisterRestClient(baseUri = "https://api.tvmaze.com")
public interface TvSeriesPersonProxy {
    @GET
    @Path("people")
    JsonArray getTvSeriesByPerson(@QueryParam("q")String personName);
}
