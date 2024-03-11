package org.masonord.response;

import org.masonord.OutputHandler;

import java.io.IOException;

public class ResponseInteger implements Response<Integer>{
    private Integer value;

    public ResponseInteger() {

    }

    public ResponseInteger(Integer value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return value;
    }

    @Override
    public void write(OutputHandler outputHandler) throws IOException {
        outputHandler.write(value);
    }
}
