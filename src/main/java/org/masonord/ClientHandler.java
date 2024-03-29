package org.masonord;

import org.masonord.command.CommandInterface;
import org.masonord.command.QuitCommand;
import org.masonord.exception.InvalidCommand;
import org.masonord.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.Socket;
import java.util.concurrent.*;

public class ClientHandler implements Callable<Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);
    private final Socket clientSocket;
    private static final ExecutorService service = Executors.newSingleThreadExecutor();

    public ClientHandler(Socket clientSocket) {
        LOGGER.info("New client connected to the server");
        this.clientSocket = clientSocket;
    }

    @Override
    public Object call() throws InvalidCommand {

        try {
            InputHandler in = new InputHandler(clientSocket.getInputStream());
            OutputHandler out = new OutputHandler(clientSocket.getOutputStream());

            while (in.hasNext()) {
                try {
                    CommandInterface<?> command = in.next();

                    if (command instanceof QuitCommand) {
                        break;
                    }

                    Response<?> response = execute(command);
                    response.write(out);
                }catch (InvalidCommand e) {
                    out.write(e);
                }
            }
            clientSocket.close();
        }catch (Exception e) {
            LOGGER.error("Connection is closed: " + e.getMessage());
            throw new InvalidCommand("Execution exception", e);
        }

        return null;
    }

    private Response<?> execute(CommandInterface<?> command) throws InvalidCommand {
        try {
            return service.submit((Callable<Response<?>>) command::execute).get();
        }catch(InterruptedException e) {
            LOGGER.error("Thread was interrupted by: " + e.getMessage());
            throw new InvalidCommand("Thread was interrupted by ", e);
        }catch(ExecutionException e) {
            if (e.getCause() != null && e.getCause() instanceof InvalidCommand) {
                LOGGER.error("Thread was interrupted by: " + e.getMessage());
                throw (InvalidCommand) e.getCause();
            }
            LOGGER.error("Execution exception: " + e.getMessage());
            throw new InvalidCommand("Execution exception", e);
        }
    }
}
