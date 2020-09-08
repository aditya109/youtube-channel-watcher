package org.example.domain;

import java.util.List;

public class Params {
    public String key;
    public List<String> value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Params{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
