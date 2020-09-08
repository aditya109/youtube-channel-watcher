package org.example.core.cmd;

import org.example.domain.ApiRequest;
import org.example.domain.ApiRequestSingleton;
import org.example.domain.Params;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

public class ExternalApiHandler {
    private static HttpURLConnection connection;

    private static String URI = "";

    public String makeApiCall(ApiRequest apiRequest) {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        URI = formURI(apiRequest);
        try {
            URL url = new URL(URI);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            }
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return responseContent.toString();

    }

    public String formURI(ApiRequest apiRequest) {
        String URL = apiRequest.getUrl();
        URL += "/" + apiRequest.getType();
        URL += "?";
        Iterator<Params> iterator = apiRequest.getParams().iterator();
        while(iterator.hasNext()) {
            Params kv = iterator.next();
            Iterator<String> stringIterator = kv.getValue().iterator();
            while(stringIterator.hasNext()) {
                String value = stringIterator.next();
                URL += kv.getKey() + "=" + value + "&";
            }
        }
        URL = URL.substring(0, URL.length()-1);

        return URL;
    }

}
