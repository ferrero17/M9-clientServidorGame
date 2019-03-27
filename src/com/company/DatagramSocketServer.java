package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class DatagramSocketServer {
    DatagramSocket socket;

    int numToResolve = (int) (Math.random()*100);

    public void init(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public void runServer() throws IOException {
        byte [] receivingData = new byte[4];
        byte [] sendingData;
        InetAddress clientIP;
        int clientPort;

//el servidor atén el port indefinidament
        while(true){
//creació del paquet per rebre les dades
            DatagramPacket packet = new DatagramPacket(receivingData, 4);
//espera de les dades
            socket.receive(packet);
//processament de les dades rebudes i obtenció de la resposta
            sendingData = processData(packet.getData(), packet.getLength());
//obtenció de l'adreça del client
            clientIP = packet.getAddress();
//obtenció del port del client
            clientPort = packet.getPort();
//creació del paquet per enviar la resposta
            packet = new DatagramPacket(sendingData, sendingData.length,
                    clientIP, clientPort);
//enviament de la resposta
            socket.send(packet);
        }
    }

    private byte[] processData(byte[] data, int length) {

        int nombre = ByteBuffer.wrap(data).getInt(); //d'array de bytes a integer
        byte[] missatge;
        if (nombre==numToResolve){
            missatge = ByteBuffer.allocate(4).putInt(0).array(); //de integer a array de bytes
        }else if (nombre>numToResolve){
            missatge = ByteBuffer.allocate(4).putInt(1).array(); //de integer a array de byte
        }else{
            missatge = ByteBuffer.allocate(4).putInt(-1).array(); //de integer a array de bytes
        }
        return missatge;

    }

}