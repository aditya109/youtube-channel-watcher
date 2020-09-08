package org.example.domain;

import java.util.List;

public class ApiRequest {
    private String type;
    private String url;
    private List<Params> params;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Params> getParams() {
        return params;
    }

    public void setParams(List<Params> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "ApiRequest{" +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", params=" + params +
                '}';
    }
}