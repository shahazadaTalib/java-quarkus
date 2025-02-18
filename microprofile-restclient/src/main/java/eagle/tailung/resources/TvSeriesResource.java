package eagle.tailung.resources;

import io.vertx.core.json.JsonArray;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/tvseries/")
public class TvSeriesResource {

    @RestClient
    TvSeriesIdProxy proxy;
    @RestClient
    TvSeriesPersonProxy personProxy;
    @GET
    @Fallback(fallbackMethod = "getTvSeriesByIdFallback")
    @Path("{id}")
    public Response getTvSeriesById(@PathParam("id") int id){
        return Response.ok(proxy.getTvSeriesById(id)).build();
    }
    public Response getTvSeriesByIdFallback(int id){
        return Response.ok("Site is under maintenance").build();
    }
    @GET
    @Path("/person/{personName}")
    public JsonArray getTvSeriesByPerson(@PathParam("personName")String personName){
        return personProxy.getTvSeriesByPerson(personName);
    }

}
