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
        }
    }

    private static void cl(){
        System.out.println("Please enter the IP of the Server. (127.0.0.1)");
        String ip = sc.nextLine();
        System.out.println("Please enter the Port of the Server. (1337)");
        String port = sc.nextLine();
        Client cl = new Client("Test", ip, Integer.parseInt(port));
        try {
            cl.startClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void se() {
        try {
            Server se = new Server(1337);
            se.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


