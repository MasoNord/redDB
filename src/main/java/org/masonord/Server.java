package org.masonord;

import org.masonord.exception.InternalServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server  {
    private static final int PORT = 6379;
    private static final int POOL = 10;

    private ExecutorService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    private Server() {init();}

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

    public static void main(String[] args) {
        new Server().start();
    }
}

