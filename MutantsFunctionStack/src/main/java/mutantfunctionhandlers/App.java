package mutantfunctionhandlers;

import java.util.HashMap;
import java.util.Map;

import Business.DTOs.IsMutantDTO;
import Business.DTOs.IsMutantDTOResponse;

import ExternalServices.CacheService;
import Services.AdnMutantScanner;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;


import com.google.gson.*;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);
        try {
            IsMutantDTO mutantDTO = new Gson().fromJson(input.getBody(), IsMutantDTO.class);
            AdnMutantScanner mutantScanner = new AdnMutantScanner();
            /*for (String d : mutantDTO.getDna()){
                System.out.println(d);
            }*/
            IsMutantDTOResponse mutantStatus = mutantScanner.scanDna(mutantDTO.getDna());

            return response
                    .withStatusCode(mutantStatus.getStatusCode())
                    .withBody(String.valueOf(mutantStatus.isState()));
        }catch (Exception e) {
            System.out.println(e);
            return response
                    .withBody("Internal Error")
                    .withStatusCode(500);
        }


    }
}
