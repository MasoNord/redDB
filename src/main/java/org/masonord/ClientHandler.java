package org.masonord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ClientHandler implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            List<String> inputParams;

            while ((inputParams = parseInput(bufferedReader)) != null) {
                LOGGER.debug("inputParams: {}", inputParams);
                RedisExecutor.execute(bufferedWriter, inputParams);
            }
        } catch (IOException e) {
            LOGGER.error("create I/O stream error.", e);
        }finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            }catch (IOException e) {
                LOGGER.error("close socket error", e);
            }
        }
    }

    private List<String> parseInput(BufferedReader reader) throws IOException {
        try {
            String str = reader.readLine();
            List<String> list = new ArrayList<>();

            if (Objects.isNull(str)) {
                return null;
            }

            int size = Integer.parseInt(str.substring(1));
            for (int i = 0; i < size; i++) {
                String paramStr = reader.readLine();
                String param = reader.readLine();
                list.add(param);
            }

            return list;
        }catch (Exception e) {
            LOGGER.warn("parse input failed", e);
            return null;
        }
    }
}
