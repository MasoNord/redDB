package org.masonord.response;

import org.masonord.OutputHandler;

import java.io.IOException;

public class ResponseString implements Response<String>{
    private String value;

    public ResponseString() {

    }

    public ResponseString(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value == null ? null : value;
    }

    @Override
    public void write(OutputHandler outputHandler) throws IOException {
        outputHandler.write(value);
    }
}
