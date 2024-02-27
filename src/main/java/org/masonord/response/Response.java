package org.masonord.response;

import org.masonord.OutputHandler;

import java.io.IOException;

public interface Response<T> {

    T value();

    void write(OutputHandler outputHandler) throws IOException;

}
