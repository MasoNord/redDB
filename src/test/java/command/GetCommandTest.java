package command;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.masonord.command.CommandInterface;
import org.masonord.command.GetCommand;
import org.masonord.command.SetCommand;
import org.masonord.exception.InvalidCommand;
import org.masonord.persistence.InMemoryDB;
import org.masonord.response.Response;

public class GetCommandTest {

    private final String[] NO_ARGS = {};
    private String[] ONE_ARG = new String[] {"Greeting"};
    private String[] TWO_ARGS = new String[] {"Greeting", "Hello"};

    @BeforeEach
    public void setup() {
        InMemoryDB.INSTANCE.clear();
    }

    @Test
    public void should_throw_exception_when_provided_no_args() {
        Exception exception = Assert.assertThrows(InvalidCommand.class, () -> new GetCommand(NO_ARGS));
        Assertions.assertEquals("Not enough args", exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_provided_more_than_two_args() {
        Exception exception = Assert.assertThrows(InvalidCommand.class, () -> new GetCommand(TWO_ARGS));
        Assertions.assertEquals("Not enough args", exception.getMessage());
    }

    @Test
    public void should_successfully_get_value_by_key() throws InvalidCommand {
        CommandInterface<?> command = new SetCommand(TWO_ARGS);
        command.execute();

        command = new GetCommand(ONE_ARG);
        Response<?> response = command.execute();

        Assertions.assertEquals("Hello", response.value());
    }

    @Test
    public void should_throw_exception_if_provided_key_does_not_exist() throws InvalidCommand {
        CommandInterface<?> command = new GetCommand(ONE_ARG);
        Exception exception = Assert.assertThrows(InvalidCommand.class, command::execute);
        Assertions.assertEquals("The key not found", exception.getMessage());
    }
}
