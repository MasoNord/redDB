package org.masonord.command;

import org.masonord.exception.InvalidCommand;

public class CommandFactory {
    public static CommandFactory INSTANCE = new CommandFactory();

    public CommandInterface<?> createCommand(String name, String[] args) throws InvalidCommand{
        return switch (name) {
            case "GET" -> new GetCommand(args);
            case "SET" -> new SetCommand(args);
            case "ECHO" -> new EchoCommand(args);
            case "PING" -> new PingCommand();
            case "QUIT" -> new QuitCommand(args);
            case "DEL" -> new DelCommand(args);
            case "EXISTS" -> new ExistsCommand(args);
            default -> throw new InvalidCommand("Cannot find command type");
        };
    }
}
