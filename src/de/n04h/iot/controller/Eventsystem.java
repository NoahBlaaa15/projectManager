package de.n04h.iot.controller;

import java.util.ArrayList;
import java.util.List;


interface ServerListener {
    void responseServer(String message);
    //TODO: Add functions for other messages
}

interface ClientListener {
    void responseClient(String message);
    //TODO: Add functions for other messages
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

    public void requestServer(String message){
        for(ServerListener sl : servers){
            //TODO: Look at message (switch)
            sl.responseServer(message);
        }
    }

    public void requestClient(String message){
        for(ClientListener sl : clients){
            //TODO: Look at message (switch)
            sl.responseClient(message);
        }
    }
}