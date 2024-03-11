package command;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.masonord.command.CommandInterface;
import org.masonord.command.DelCommand;
import org.masonord.command.GetCommand;
import org.masonord.command.SetCommand;
import org.masonord.exception.InvalidCommand;
import org.masonord.response.Response;
import org.masonord.response.ResponseInteger;

import java.net.Inet4Address;

public class DelCommandTest {
    private final String[] NO_ARGS = {};
    private final String[] TWO_ARGS = new String[] {"first", "second"};
    private final String[] FIRST_KEY = new String[] {"first", "1.0"};
    private final String[] SECOND_KEY = new String[] {"second", "2.0"};


    @Test
    public void should_throw_exception_when_provided_no_args() {
        Exception exception = Assert.assertThrows(InvalidCommand.class, () -> new GetCommand(NO_ARGS));
        Assertions.assertEquals("Not enough args", exception.getMessage());
    }

    @Test
    public void should_successfully_remove_couple_of_keys() throws InvalidCommand {
        CommandInterface<?> command = new SetCommand(FIRST_KEY);
        command.execute();
        command = new SetCommand(SECOND_KEY);
        command.execute();


        command = new DelCommand(TWO_ARGS);
        Response<?> response = command.execute();

        Assertions.assertEquals(2, response.value());
    }

    @Test
    public void should_return_zero_if_provided_non_existing_key() throws InvalidCommand {
        CommandInterface<?> command = new DelCommand(TWO_ARGS);
        Response<?> response = command.execute();

        Assertions.assertEquals(0, response.value());
    }

}
