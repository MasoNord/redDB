package org.masonord;

import org.masonord.command.CommandFactory;
import org.masonord.command.CommandInterface;
import org.masonord.exception.InvalidCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class InputHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(InputHandler.class);
    private final BufferedInputStream stream;

    public InputHandler(InputStream stream) {
        this.stream = new BufferedInputStream(stream);
    }

    public CommandInterface<?> next() throws IOException, InvalidCommand {
        if (!hasNext()) {
            return null;
        }

        String[] command = command(length());

        String commandType = command[0];
        String[] args = Arrays.copyOfRange(command, 1, command.length);

        return CommandFactory.INSTANCE.createCommand(commandType, args);
    }

    public boolean hasNext() throws IOException {
        stream.mark(1);
        if (stream.read() == -1)
            return false;

        stream.reset();
        return true;
    }

    private String arg() throws IOException {
        check(Protocol.DOLLAR);
        byte[] b = new byte[readNum()];

        stream.read(b);
        check((byte) '\r');
        check((byte) '\n');

        return new String(b);
    }

    private String[] command(int l) throws IOException {
        String[] c = new String[l];
        for (int i = 0; i < l; i++) {
            c[i] = arg();
        }
        return c;
    }

    private int length() throws IOException {
        check(Protocol.ASTERISK);
        return readNum();
    }

    private int readNum() throws IOException {
        char c;
        StringBuilder stringBuilder = new StringBuilder();
        while ((c = (char) stream.read()) != '\r') {
            stringBuilder.append(c);
        }

        check((byte) '\n');
        return Integer.parseInt(stringBuilder.toString());
    }

    private void check(byte var) throws IOException {
        if (stream.read() != var) {
            LOGGER.error("Invalid argument supplied");
            throw new RuntimeException("Invalid argument supplied");
        }
    }
}
