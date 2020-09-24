package de.n04h.iot.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    String ip;
    Integer port;
    String name;

    Socket serverConnection;

    public Client(String name, String ip, Integer port) {
        System.out.println("Client starting...");
        this.name = name;
        this.port = port;
        this.ip = ip;
    }

    public void startClient() throws IOException {
        System.out.println("Connecting to Server: " + name + "@" + ip + ":" + port);

        serverConnection = new Socket(ip, port);
    }

    public OutputStream getOutputStream() throws IOException {
        return serverConnection.getOutputStream();
    }

    public InputStream getInputStream() throws IOException {
        return serverConnection.getInputStream();
    }

}
