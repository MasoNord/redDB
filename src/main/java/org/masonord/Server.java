package org.masonord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;

public class Server  {
    private Server() {}

    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    public static ServerSocket createRedisServer(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }));

            return serverSocket;
        } catch (IOException e) {
            LOGGER.error("IOException", e);
            throw new RuntimeException(String.format("IOException: %s%n", e.getMessage()));
        }
    }
}

