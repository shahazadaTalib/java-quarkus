package eagel.tailung.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/")
public class Resource {
    @ConfigProperty(name = "CEO",defaultValue = "CD")
    String ceo;

    @GET
    @Path("ceo")
    public Response getCEOName(){
        return Response.ok(ceo).build();
    }
}
