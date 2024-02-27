package org.masonord;

import org.masonord.command.CommandInterface;
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
        this.clientSocket = clientSocket;
    }

    @Override
    public Object call() throws Exception {

        try {
            InputHandler in = new InputHandler(clientSocket.getInputStream());
            OutputHandler out = new OutputHandler(clientSocket.getOutputStream());

            while (in.hasNext()) {
                try {
                    CommandInterface<?> command = in.next();

                    Response<?> response = execute(command);
                    response.write(out);


                }catch (InvalidCommand e) {
                    out.write(e);
                }
            }
            clientSocket.close();
        }catch (Exception e) {
            // TODO: logging
        }

        return null;
    }

    private Response<?> execute(CommandInterface<?> command) throws InvalidCommand {
        try {
            return service.submit((Callable<Response<?>>) () -> command.execute()).get();
        }catch(InterruptedException e) {
            throw new InvalidCommand("Thread was interrupted by ", e);
        }catch(ExecutionException e) {
            if (e.getCause() != null && e.getCause() instanceof InvalidCommand) {
                throw (InvalidCommand) e.getCause();
            }
            throw new InvalidCommand("Execution exception", e);
        }
    }
}
