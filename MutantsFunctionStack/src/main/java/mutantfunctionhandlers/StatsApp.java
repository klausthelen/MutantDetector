package mutantfunctionhandlers;

import Business.DTOs.StatsDTO;
import Services.DnaMutantStats;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class StatsApp implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);
        try {
            DnaMutantStats dnaMutantStats = new DnaMutantStats();
            StatsDTO mutantStats = dnaMutantStats.getMutantStats();
            String jsonInString = new Gson().toJson(mutantStats);
            return response
                    .withStatusCode(200)
                    .withBody(jsonInString);
        }catch (Exception e) {
            System.out.println(e);
            return response
                    .withBody("Internal Error")
                    .withStatusCode(500);
        }


    }
}
