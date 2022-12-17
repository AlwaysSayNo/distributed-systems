package org.grynko.nazar.rmi;


import org.grynko.nazar.rmi.server.ServerMain;
import org.grynko.nazar.rmi.client.ClientMain;

public class Main {

    public static void main(String[] args) {
        Thread serverThread = new Thread(
                () -> ServerMain.main(args)
        );

        Thread clientThread = new Thread(
                () -> ClientMain.main(args)
        );


        serverThread.start();
        clientThread.start();
    }

}
