package org.masonord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        LOGGER.info("Logs from your program will appear here!");
        ServerSocket serverSocket = Server.createRedisServer(Constants.REDIS_PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientHandler redisClientThread = new ClientHandler(clientSocket);
            redisClientThread.run();
        }
    }
}
