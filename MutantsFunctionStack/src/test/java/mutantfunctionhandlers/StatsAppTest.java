package mutantfunctionhandlers;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StatsAppTest {
    @Test
    public void statsAppTest() {
        APIGatewayProxyRequestEvent input = new APIGatewayProxyRequestEvent();
        input.setBody("{\"dna\":[\"AGGCGA\",\"ATGGTA\",\"TTAGGT\",\"ATCGGA\",\"ACCGTA\",\"TGATCT\"]}");
        StatsApp  statsApp = new StatsApp();
        APIGatewayProxyResponseEvent result = statsApp.handleRequest(null, null);
        assertEquals(result.getStatusCode().intValue(), 500);
        assertEquals(result.getHeaders().get("Content-Type"), "application/json");
        String content = result.getBody();
        assertNotNull(content);
    }
}
