package de.n04h.iot.controller;


import java.io.IOException;
import java.net.*;
import java.util.*;

public class Main{

    private static Scanner sc = new Scanner(System.in);

    private static ListenerInit li = new ListenerInit();



    public static void main(String[] args) throws IOException {
        System.out.println(
                  "███╗   ██╗ ██████╗ ██╗  ██╗██╗  ██╗\n"
                + "████╗  ██║██╔═████╗██║  ██║██║  ██║\n"
                + "██╔██╗ ██║██║██╔██║███████║███████║\n"
                + "██║╚██╗██║████╔╝██║╚════██║██╔══██║\n"
                + "██║ ╚████║╚██████╔╝     ██║██║  ██║\n"
                + "╚═╝  ╚═══╝ ╚═════╝      ╚═╝╚═╝  ╚═╝.de\n"
                + "                              projectManager\n"
                + "                              \n"
                + "Do you want to start the application as a Server or Client?");


        switch (sc.nextLine().toLowerCase()){
            case "server":
            case "s":

                System.out.println("Please enter the Port of the Server. (1337)");

                String portS = sc.nextLine();
                if(portS.equalsIgnoreCase("")){
                    portS = "1337";
                }

                Server se = new Server(Integer.parseInt(portS));
                li.addServerListener(new ServerEvent());
                se.startServer(li);

                break;
            case "client":
            case "c":

                System.out.println("Please enter the IP of the Server. (127.0.0.1)");
                String ip = sc.nextLine();
                if(ip.equalsIgnoreCase("")){
                    ip = "127.0.0.1";
                }

                System.out.println("Please enter the Port of the Server. (1337)");
                String port = sc.nextLine();
                if(port.equalsIgnoreCase("")){
                    port = "1337";
                }

                Client cl = new Client(ip, Integer.parseInt(port));
                li.addClientListener(new ClientEvent());
                cl.startClient(li);

                break;
        }
    }
}

class ServerEvent implements ServerListener {
    //TODO: Implement other messages
    @Override
    public void responseServer(String message) { System.out.println(message); }
}

class ClientEvent implements ClientListener {
    //TODO: Implement other messages
    @Override
    public void responseClient(String message) { System.out.println(message); }
}

