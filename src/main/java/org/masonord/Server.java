package org.masonord;

import org.masonord.exception.InternalServerError;
import org.masonord.exception.PropertyNotFoundException;
import org.masonord.util.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server  {
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    public final Environment env = new Environment("application.properties");
    private final int PORT = Integer.parseInt(env.getValue("port"));
    private final int POOL = Integer.parseInt(env.getValue("pool"));

    private ExecutorService service;

    private Server() throws FileNotFoundException, PropertyNotFoundException {init();}

    private void init() {
        try {
            this.service = Executors.newFixedThreadPool(POOL);
        }catch (Throwable e) {
            LOGGER.error("Caught exception during startup");
            throw new InternalServerError(e.getMessage(), e);
        }
    }

    public void start() {
        LOGGER.info("Server starting on port " + PORT + " ...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler client = new ClientHandler(clientSocket);
                service.submit(client);
            }
        }catch (Throwable e) {
            LOGGER.error("Caught exception during runtime: " + e.getMessage());
            throw new InternalServerError(e.getMessage(), e);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, PropertyNotFoundException {
        new Server().start();
    }
}

