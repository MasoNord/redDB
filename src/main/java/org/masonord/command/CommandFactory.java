package org.masonord.command;

import org.masonord.exception.InvalidCommand;

public class CommandFactory {

    public static CommandFactory INSTANCE = new CommandFactory();

    public CommandInterface<?> createCommand(String name, String[] args) throws InvalidCommand{
        CommandInterface<?> command = null;

        switch (name) {
//            case "GET":
//                command = new GetCommand(args);
//                break;
//            case "SET":
//                command = new SetCommand(name);
//                break;
//            case "ECHO":
//                command = new EchoCommand(args);
//                break;
            case "PING":
                command = new PingCommand();
                break;
            default:
                throw new InvalidCommand("Cannot find command type");
                //            case "QUIT":
//                command = new QuitCommand(args);
//                break;

        }

        return command;
    }
}
