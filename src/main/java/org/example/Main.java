package org.example;

import org.example.core.cmd.ExternalApiHandler;
import org.example.domain.ApiRequest;
import org.example.domain.ApiRequestSingleton;
import org.example.helper.ParseHandler;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        ApiRequest apiRequest = ApiRequestSingleton.getInstance();
        ExternalApiHandler externalApiHandler = new ExternalApiHandler();
        ParseHandler parseHandler = new ParseHandler();
        String stringResponse = externalApiHandler.makeApiCall(apiRequest);
        JSONObject jsonResponseObject = parseHandler.parseResponseJSON(stringResponse);


    }
}
