package com.company;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {


        String ip;
        Scanner scanner = new Scanner(System.in);

        DatagramSocketClient datagramSocketClient = new DatagramSocketClient();

        System.out.println("A que IP del Servidor quieres conectarte? ");
        ip = scanner.nextLine();


        datagramSocketClient.init(ip,5557);
        datagramSocketClient.runClient();


    }
}
