package de.n04h.iot.controller;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class Server {

    Integer port;

    ServerSocket serverSocket;
    Socket clientSocket;

    long millis;

    public Server(Integer port) {
        System.out.println("Server starting...");
        this.port = port;
        try {
            System.out.println("With Local-IP: " + InetAddress.getLocalHost().getHostAddress() + " and Port: " + port);
        } catch (UnknownHostException e) {
            System.out.println("Not connected to a Network!");
            e.printStackTrace();
        }
    }

    public void startServer(ListenerInit li) {
        System.out.println("Opening Port: " + port);

        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Port blocked!");
            e.printStackTrace();
        }


        System.out.print("Status: Connected; Time: 0");
        millis = System.currentTimeMillis();

        Thread newThread = new Thread(() -> {
            while(true) {
                System.out.print("\r");
                System.out.print("Status: Connected; Time(sec): " + (System.currentTimeMillis() - millis) / 1000);

                try {
                    getOutputStream().println("Connected");
                    li.requestServer(getInputStream().readLine());
                } catch (IOException e) {
                    System.out.println("\nClient disconnected! Reopening...");
                    try {
                        clientSocket.close();
                        clientSocket = serverSocket.accept();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    millis = System.currentTimeMillis();
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
