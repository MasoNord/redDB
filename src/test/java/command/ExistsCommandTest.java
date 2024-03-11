package command;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.masonord.command.*;
import org.masonord.exception.InvalidCommand;
import org.masonord.response.Response;

public class ExistsCommandTest {

    private final String[] NO_ARGS = {};
    private String[] TWO_ARGS = new String[] {"Greeting", "Hello"};

    @Test
    public void should_throw_exception_when_provided_no_args() {
        Exception exception = Assert.assertThrows(InvalidCommand.class, () -> new GetCommand(NO_ARGS));
        Assertions.assertEquals("Not enough args", exception.getMessage());
    }

    @Test
    public void should_return_count_for_found_keys() throws InvalidCommand {
        CommandInterface<?> command = new SetCommand(TWO_ARGS);
        command.execute();
        command = new ExistsCommand(new String[] {TWO_ARGS[0]});
        Response<?> response = command.execute();

        Assertions.assertEquals(response.value(), 1);
    }

    @Test
    public void should_return_count_fro_not_found_key() throws InvalidCommand {
        CommandInterface<?> command = new ExistsCommand(new String[] {TWO_ARGS[0]});
        Response<?> response = command.execute();

        Assertions.assertEquals(response.value(), 0);
    }
}
