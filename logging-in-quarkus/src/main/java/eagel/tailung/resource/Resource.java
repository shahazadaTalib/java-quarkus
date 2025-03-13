package eagel.tailung.resource;

import eagel.tailung.dto.Account;
import eagel.tailung.service.AccountService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Resource {
    private static final Logger LOGGER = Logger.getLogger(Resource.class);
    @Inject
    AccountService accountService;
    @POST
    @Path("open_account")
    public Response openAccount(@RequestBody Account account){
        LOGGER.info("Entering in Response::openAccount()");
        LOGGER.debug("Response::openAccount() account "+account);
        boolean alreadyExist = accountService.isAccountAlreadyExist(account);
        if(alreadyExist){
            LOGGER.info("Returning from openAccount::openAccount");
            return Response.ok("Oops! Account already Exist").build();
        }
        LOGGER.info("Returning from openAccount::openAccount");
        return Response.ok("Thanks for opening account....").build();

    }
}
