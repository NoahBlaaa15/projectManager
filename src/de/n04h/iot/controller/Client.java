package de.n04h.iot.controller;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    String ip;
    Integer port;

    Socket serverConnection;

    long millis;

    public Client(String ip, Integer port) {
        System.out.println("Client starting...");
        this.port = port;
        this.ip = ip;
    }

    public void startClient() {
        System.out.println("Connecting to Server: " + ip + ":" + port);

        try {
            serverConnection = new Socket(ip, port);
        } catch (IOException e) {
            System.out.println("Cant connect to Server. Exiting...");
            System.exit(0);
        }

        System.out.print("Status: Connected; Time(sec): 0");
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
        PrintWriter out = new PrintWriter(serverConnection.getOutputStream(), true);
        return out;
    }

    public BufferedReader getInputStream() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));
        return in;
    }

}
