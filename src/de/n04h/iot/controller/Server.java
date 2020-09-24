package de.n04h.iot.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    Integer port;

    ServerSocket serverSocket;
    Socket clientSocket;

    public Server(Integer port) throws UnknownHostException {
        System.out.println("Server starting...");
        this.port = port;
        System.out.println("With Local-IP: " + InetAddress.getLocalHost().getHostAddress() + " and Port: " + port);
    }

    public void startServer() throws IOException {
        System.out.println("Opening Port: " + port);

        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
    }

    public OutputStream getOutputStream() throws IOException {
        return clientSocket.getOutputStream();
    }

    public InputStream getInputStream() throws IOException {
        return clientSocket.getInputStream();
    }

}
