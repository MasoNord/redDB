package org.masonord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements ClientInterface{
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    @Override
    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public String sendMessage(String msg) {
        String resp = "";
        try {
            out.println(msg);
            resp = in.readLine();
        }catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
        return resp;
    }

    @Override
    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        }catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
