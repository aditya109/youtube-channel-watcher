package org.example.domain;

import org.example.helper.ParseHandler;

import java.io.IOException;

public class ApiRequestSingleton {
    private static ApiRequest apiRequestSingleton;

    private ApiRequestSingleton() {
    }

    public static ApiRequest getInstance() {
        if (apiRequestSingleton == null) {
            ParseHandler getPropertyValues = new ParseHandler();
            try {
                apiRequestSingleton = getPropertyValues.parseConfigYAML();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return apiRequestSingleton;
    }
}
