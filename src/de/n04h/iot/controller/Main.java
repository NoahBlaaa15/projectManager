package de.n04h.iot.controller;


import java.io.IOException;
import java.net.*;
import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);

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
                se();
                break;
            case "client":
                cl();
                break;
            case "s":
                se();
                break;
            case "c":
                cl();
                break;
        }
    }

    private static void cl(){
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
        cl.startClient();
    }

    private static void se() {
        try {
            System.out.println("Please enter the Port of the Server. (1337)");
            String port = sc.nextLine();
            if(port.equalsIgnoreCase("")){
                port = "1337";
            }
            Server se = new Server(Integer.parseInt(port));
            se.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


