package eagle.tailung.resources;

import io.vertx.core.json.JsonArray;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/tvseries/")
public class TvSeriesResource {

    @RestClient
    TvSeriesIdProxy proxy;
    @RestClient
    TvSeriesPersonProxy personProxy;
    @GET
    @Path("{id}")
    public TvSeries getTvSeriesById(@PathParam("id") int id){
        return proxy.getTvSeriesById(id);
    }
    @GET
    @Path("/person/{personName}")
    public JsonArray getTvSeriesByPerson(@PathParam("personName")String personName){
        return personProxy.getTvSeriesByPerson(personName);
    }

}
