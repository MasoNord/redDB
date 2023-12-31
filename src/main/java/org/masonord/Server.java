package org.masonord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements ServerInterface{
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;


    @Override
    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())));
            String greeting = in.readLine();

            if ("hello server".equals(greeting))
                out.println("hello client");
            else
                out.println("unrecognised greeting");

        }catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        }catch(IOException exception){
            System.out.println(exception.getMessage());
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start(8080);
    }
}
