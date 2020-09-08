package org.example.helper;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class GetPropertyValuesTest {

    @Test
    void getPropValues() {
        ParseHandler parseHandler = new ParseHandler();
        try {
            parseHandler.parseConfigYAML();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}