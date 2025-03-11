package eagel.tailung.resource;

import eagel.tailung.config.CustomConfig;
import eagel.tailung.constant.Constants;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;

@Path("/")
public class Resource {
    @Inject
    CustomConfig customConfig;
    @ConfigProperty(name="interest_rate",defaultValue = "10")
    int interestRate;
    @Path("calc_interest/{amount}")
    @GET
    public Response calcInterest(@PathParam("amount") int amount){
       //return  Response.ok((amount* Constants.INTEREST_RATE*1)/100).build();
        return Response.ok(amount*interestRate/100).build();
    }

    @GET
    @Path("calc_interest/{branch}/{amount}")
    public Response calcInterest(@PathParam("branch") String branch,@PathParam("amount") int amount){
//        Integer value = ConfigProvider
//                .getConfig()
//                .getValue(branch.toLowerCase()+"_interest_rate", Integer.class);
        Integer value = ConfigProvider
                .getConfig()
                .getOptionalValue(branch.toLowerCase()+"_interest_rate",Integer.class)
                .orElse(5);
        return Response.ok((amount*value.intValue())/100).build();
    }
    @GET
    @Path("cal_branch/{branch}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response calBranch(@PathParam("branch") String branch){
//        List<String> branchList=ConfigProvider.getConfig().getOptionalValues("branch_list",String.class)
//                .get().stream().filter(n->n.equals(branch)).toList();
        List<String>branchList = ConfigProvider.getConfig().getOptionalValues("branch_list",String.class)
                .orElse(List.of()).stream().filter(n->n.equals(branch)).toList();
        return branchList.isEmpty()?Response.status(Response.Status.BAD_REQUEST).entity("No Data Found").build():Response.ok(branchList).build();
    }
}
