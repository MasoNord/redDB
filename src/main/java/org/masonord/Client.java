package org.masonord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable{
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    @Override
    public void run() {
        try{
            client = new Socket("127.0.0.1",6379);
            out = new PrintWriter(client.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            InputHandler inHandler = new InputHandler();
            Thread t = new Thread(inHandler);
            t.start();

            String inMessage;
            while ((inMessage = in.readLine()) != null){
                System.out.println(inMessage);
            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    class InputHandler implements Runnable{
        @Override
        public void run() {
            try{
                BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
                while (true){
                    String message = inReader.readLine();
                    if ("PING".equalsIgnoreCase(message)) {
                        out.println("+PONG\r\n");
                        out.flush();
                    }
                }
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}
