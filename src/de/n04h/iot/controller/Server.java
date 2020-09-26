package de.n04h.iot.controller;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

interface ServerListener {
    void response();
}

class ServerInit {
    private List<ServerListener> listeners = new ArrayList<>();

    public void addListener(ServerListener add){
        listeners.add(add);
    }

    public void request(){
        for(ServerListener sl : listeners){
            sl.response();
        }
    }
}

class ServeResp implements ServerListener{
    @Override
    public void response(){

    }
}

public class Server {

    Integer port;

    ServerSocket serverSocket;
    Socket clientSocket;

    long millis;

    public Server(Integer port) throws UnknownHostException {
        System.out.println("Server starting...");
        this.port = port;
        System.out.println("With Local-IP: " + InetAddress.getLocalHost().getHostAddress() + " and Port: " + port);
    }

    public void startServer() throws IOException {
        System.out.println("Opening Port: " + port);

        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();

        System.out.print("Status: Connected; Time: 0");
        millis = System.currentTimeMillis();

        Thread newThread = new Thread(() -> {
            while(true) {
                System.out.print("\r");
                System.out.print("Status: Connected; Time(sec): " + (System.currentTimeMillis() - millis) / 1000);

                try {
                    getOutputStream().println("Connected");
                    getInputStream().readLine();
                } catch (IOException e) {
                    System.out.println("\nError in connection! Exiting...");
                    System.exit(0);
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        newThread.start();
    }

    public PrintWriter getOutputStream() throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        return out;
    }

    public BufferedReader getInputStream() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return in;
    }

}
