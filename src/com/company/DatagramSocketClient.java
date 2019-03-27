package com.company;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class DatagramSocketClient {
    InetAddress serverIP;
    int serverPort;
    DatagramSocket socket;
    boolean seguir = true;

    public void init(String host, int port) throws SocketException,
            UnknownHostException {
        serverIP = InetAddress.getByName(host);
        serverPort = port;
        socket = new DatagramSocket();
    }

    public void runClient() throws IOException {
        byte [] receivedData = new byte[4];
//a l'inici


//el servidor atén el port indefinidament
        while(seguir){

            System.out.println("Que número quieres BRO");

            Scanner scanner = new Scanner(System.in);

            int datoEnviar = scanner.nextInt();
            scanner.nextLine();

            byte[] sendingData = ByteBuffer.allocate(4).putInt(datoEnviar).array(); //de integer a array de bytes
            DatagramPacket packet = new DatagramPacket(sendingData,
                    sendingData.length,
                    serverIP,
                    serverPort);
//enviament de la resposta
            socket.send(packet);

//creació del paquet per rebre les dades
            packet = new DatagramPacket(receivedData, 4);
//espera de les dades
            socket.receive(packet);
//processament de les dades rebudes i obtenció de la resposta

            int datosRecibidos = ByteBuffer.wrap(receivedData).getInt(); //d'array de bytes a integer

            if(datosRecibidos == 0){
                seguir=false;
                System.out.println("Has ganado Bro");
            }else if(datosRecibidos == 1){
                System.out.println("EL NUMERO ES MENOR");
            }else if (datosRecibidos == -1){
                System.out.println("EL NUMERO ES MAYOR");
            }
        }
    }
}