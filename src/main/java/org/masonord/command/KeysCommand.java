package org.masonord.command;

import org.masonord.exception.InvalidCommand;
import org.masonord.persistence.InMemoryDB;
import org.masonord.response.Response;
import org.masonord.response.ResponseString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeysCommand implements CommandInterface<String> {
    private final Pattern pattern;

    public KeysCommand(String[] args) throws InvalidCommand {
        if (args.length != 1) {
            throw new InvalidCommand("Not enough arguments");
        }
        this.pattern = Pattern.compile(args[0], Pattern.CASE_INSENSITIVE);
    }

    @Override
    public Response<String> execute() throws InvalidCommand {
        StringBuilder response = new StringBuilder();

        synchronized (InMemoryDB.INSTANCE) {
            int count = 1;
            for (String key: InMemoryDB.INSTANCE.getKeySet()) {
                Matcher matcher = pattern.matcher(key);

                if (matcher.matches()) {
                    response.append(count);
                    response.append(") ");
                    response.append(key);
                    response.append(System.lineSeparator());
                    count++;
                }
            }
        }

        return new ResponseString(response.toString());
    }
}
