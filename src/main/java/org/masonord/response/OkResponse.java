package org.masonord.response;

import org.masonord.OutputHandler;

import java.io.IOException;

public class OkResponse implements Response<String> {
    public static final String response = "OK";

    @Override
    public String value() {
        return null;
    }

    @Override
    public void write(OutputHandler outputHandler) throws IOException {
        outputHandler.writeOk();
    }
}
