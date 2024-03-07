package org.masonord;

import org.masonord.exception.InvalidCommand;
import org.masonord.response.OkResponse;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class OutputHandler {

    private final OutputStream stream;

    public OutputHandler(OutputStream stream) {
        this.stream = new BufferedOutputStream(stream);
    }

    public void write(String value) throws IOException {
        if (value == null) {
            stream.write('$');
            stream.write('-');
            stream.write('1');
            stream.write('\r');
            stream.write('\n');
        }else {
            stream.write(Protocol.DOLLAR);
            stream.write(Protocol.toBytes(value.length()));
            stream.write('\r');
            stream.write('\n');
            for (int i = 0; i < value.length(); i++) {
                stream.write(value.charAt(i));
            }
            stream.write('\r');
            stream.write('\n');
            stream.flush();
        }
    }

    public void write(int number) throws IOException {
        stream.write(Protocol.COLON);
        stream.write(Protocol.toBytes(number));
        stream.write('\r');
        stream.write('\n');
        stream.flush();
    }

    public void writeOk() throws IOException {
        stream.write(Protocol.PLUS);
        stream.write(Protocol.toBytes(OkResponse.response));

        stream.write('\r');
        stream.write('\n');

        stream.flush();
    }

    public void write(InvalidCommand invalidCommand) throws IOException {
        String message = invalidCommand.getMessage();

//        stream.write(Protocol.MINUS);
        stream.write(Protocol.toBytes("-ERR "));
        if (!Objects.isNull(message)) {
            stream.write(Protocol.toBytes(message));
            stream.write('\r');
            stream.write('\n');
        }
        stream.flush();
    }
}
