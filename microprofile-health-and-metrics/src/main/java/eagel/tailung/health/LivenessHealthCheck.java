package eagel.tailung.health;

import eagel.tailung.restclient.JsonPlaceHolderRestClient;
import jakarta.inject.Inject;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Liveness
public class LivenessHealthCheck implements HealthCheck {
    @RestClient
    JsonPlaceHolderRestClient restClient;
    @Override
    public HealthCheckResponse call() {
        restClient.getAllPost();
        return HealthCheckResponse.named("JsonPLaceHolder APIs Health").up().build();
    }
}
