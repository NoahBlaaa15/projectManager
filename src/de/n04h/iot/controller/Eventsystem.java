package de.n04h.iot.controller;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


interface ServerListener {
    void startProgram(PrintWriter outputStream);
    void stopProgram(PrintWriter outputStream);
    void responseStatus(PrintWriter outputStream);
}

interface ClientListener {
    void responseStatus(String message);
    void programStarted();
    void programStopped();
}

class ListenerInit {
    private List<ServerListener> servers = new ArrayList<>();
    private List<ClientListener> clients = new ArrayList<>();

    public void addServerListener(ServerListener add){
        servers.add(add);
    }

    public void addClientListener(ClientListener add){
        clients.add(add);
    }

    public void requestServer(String message, PrintWriter outputStream, BufferedReader inputStream){
        for(ServerListener sl : servers){
            switch (message){
                case "start":
                    sl.startProgram(outputStream);
                    break;
                case "stop":
                    sl.stopProgram(outputStream);
                    break;
                case "status":
                    sl.responseStatus(outputStream);
                    break;
            }
        }
    }

    public void requestClient(String message, PrintWriter outputStream, BufferedReader inputStream){
        for(ClientListener cl : clients){
            switch (message){
                case "started":
                    cl.programStarted();
                    break;
                case "stopped":
                    cl.programStopped();
                    break;
                default:
                    if(message.contains("status-re")){
                        cl.responseStatus(message);
                    }
                    break;
            }
        }
    }
}