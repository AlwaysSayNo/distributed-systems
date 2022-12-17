package org.grynko.nazar.rmi.client;

public class ClientMain {

    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> RmiClient.start(args)
        );

        thread.start();
    }

}
