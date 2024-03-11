package command;


import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.masonord.command.CommandInterface;
import org.masonord.command.SetCommand;
import org.masonord.exception.InvalidCommand;
import org.masonord.persistence.InMemoryDB;


public class SetCommandTest {

    private final String[] NO_ARGS = {};
    private String[] ONE_ARG = new String[] {"Greeting"};
    private String[] TWO_ARGS = new String[] {"Greeting", "Hello"};


    @BeforeEach
    public void setup() {
        InMemoryDB.INSTANCE.clear();
    }

    @Test
    public void should_throw_exception_when_provided_no_args() {
        Exception exception = Assert.assertThrows(InvalidCommand.class, () -> new SetCommand(NO_ARGS));
        Assertions.assertEquals("Not enough args", exception.getMessage());
    }

    @Test
    public void should_throw_exception_when_provided_one_arg() {
        Exception exception = Assert.assertThrows(InvalidCommand.class, () -> new SetCommand(ONE_ARG));
        Assertions.assertEquals("Not enough args", exception.getMessage());
    }

    @Test
    public void should_successfully_set_key_to_value() throws InvalidCommand {
        CommandInterface<?> command = new SetCommand(TWO_ARGS);
        command.execute();
        Assertions.assertTrue(InMemoryDB.INSTANCE.containsKey(TWO_ARGS[0]));
    }
}
