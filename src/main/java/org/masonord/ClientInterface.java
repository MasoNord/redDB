package org.masonord;

public interface ClientInterface {

    void startConnection(String ip, int port);

    String sendMessage(String msg);

    void stopConnection();

}
