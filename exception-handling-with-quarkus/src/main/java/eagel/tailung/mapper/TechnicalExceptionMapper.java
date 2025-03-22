package eagel.tailung.mapper;

import eagel.tailung.dto.ErrorMessage;
import eagel.tailung.exception.TechnicalException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class TechnicalExceptionMapper implements ExceptionMapper<TechnicalException> {
    @Override
    public Response toResponse(TechnicalException technicalException) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(technicalException.getStatus());
        errorMessage.setMessage(technicalException.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
