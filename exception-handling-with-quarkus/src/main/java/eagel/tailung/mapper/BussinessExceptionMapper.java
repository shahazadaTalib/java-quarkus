package eagel.tailung.mapper;

import eagel.tailung.dto.ErrorMessage;
import eagel.tailung.exception.BussinessException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BussinessExceptionMapper implements ExceptionMapper<BussinessException> {
    @Override
    public Response toResponse(BussinessException exception) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(exception.getStatus());
        errorMessage.setMessage(exception.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
