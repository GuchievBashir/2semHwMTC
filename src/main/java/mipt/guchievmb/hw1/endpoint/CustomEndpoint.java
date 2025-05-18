package mipt.guchievmb.hw1.endpoint;
import org.springframework.stereotype.Component;
import java.util.UUID;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

@Component
@Endpoint(id = "custom")
public class CustomEndpoint {

  @ReadOperation
  public UUID customInfo() {
    return UUID.randomUUID();
  }
}