package org.masonord.response;

import org.masonord.OutputHandler;

import java.io.IOException;

public class ResponseInteger implements Response<Integer>{

    private final Integer value;

    public ResponseInteger(Integer value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return null;
    }

    @Override
    public void write(OutputHandler outputHandler) throws IOException {
        outputHandler.write(value);
    }
}
