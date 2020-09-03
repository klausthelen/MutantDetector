package mutantfunctionhandlers;

import Business.DTOs.IsMutantDTOResponse;

import Services.DnaMutantScanner;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import java.util.ArrayList;

public class AppTest {

  @Test
  public void appTest() {
    APIGatewayProxyRequestEvent input = new APIGatewayProxyRequestEvent();
    input.setBody("{\"dna\":[\"AGGCGA\",\"ATGGTA\",\"TTAGGT\",\"ATCGGA\",\"ACCGTA\",\"TGATCT\"]}");
    App app = new App();
    APIGatewayProxyResponseEvent result = app.handleRequest(input, null);
    assertEquals(result.getStatusCode().intValue(), 500);
    assertEquals(result.getHeaders().get("Content-Type"), "application/json");
    String content = result.getBody();
    assertNotNull(content);
  }

}
