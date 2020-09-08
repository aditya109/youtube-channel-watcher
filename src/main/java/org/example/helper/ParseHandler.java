package org.example.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.example.domain.ApiRequest;
import org.example.domain.Params;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ParseHandler {

    public JSONObject parseJSONStringToJSONObject(String JSONContent) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(JSONContent);
        } catch (JSONException err) {
            System.out.println("Error : " + err.toString());
        }
        return jsonObject;
    }

    public String parseYAMLStringToJSONString(String YAMLContent) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        Object object = objectMapper.readValue(YAMLContent, Object.class);

        ObjectMapper jsonWriter = new ObjectMapper();
        String JSONContent = jsonWriter.writeValueAsString(object);

        return JSONContent;
    }

    public ApiRequest parseConfigYAML() throws IOException {

        String propFileName = "config/config.yaml";
        ClassLoader classLoader = ParseHandler.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(propFileName)).getFile());
        String content = new String(Files.readAllBytes(file.toPath()));

        String JSONContent = parseYAMLStringToJSONString(content);
        JSONObject jsonObject = parseJSONStringToJSONObject(JSONContent);

        ApiRequest apiRequest = new ApiRequest();

        String type = jsonObject.getString("type");
        apiRequest.setType(type);

        String url = jsonObject.getString("url");
        apiRequest.setUrl(url);

        List<Params> params_l = new ArrayList<Params>();
        JSONArray params = jsonObject.getJSONArray("params");

        Iterator<Object> iterator = params.iterator();
        while (iterator.hasNext()) {
            Params item = new Params();
            JSONObject kv = (JSONObject) iterator.next();

            String key = kv.getString("key");
            item.setKey(key);

            JSONArray value = kv.getJSONArray("value");
            List<String> list = new ArrayList<String>();

            Iterator<Object> iterator1 = value.iterator();
            while (iterator1.hasNext()) {
                list.add((String) iterator1.next());
            }
            item.setValue(list);
            params_l.add(item);
        }
        apiRequest.setParams(params_l);
        return apiRequest;

    }

    public JSONObject parseResponseJSON(String response) {
        JSONObject jsonResponseObject = parseJSONStringToJSONObject(response);

//        get all items from response
        JSONArray items = jsonResponseObject.getJSONArray("items");
        Iterator<Object> iterator = items.iterator();
        while (iterator.hasNext()) {
            JSONObject item = (JSONObject) iterator.next();
            JSONObject snippet = item.getJSONObject("snippet");
            System.out.println(snippet.getString("title"));
        }
        return null;
    }
}
