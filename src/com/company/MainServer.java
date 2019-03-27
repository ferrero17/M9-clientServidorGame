package com.company;

import java.io.IOException;

public class MainServer {

    public static void main(String[] args) throws IOException {

        DatagramSocketServer dataServer = new DatagramSocketServer();
        dataServer.init(5557);

        dataServer.runServer();

    }
}