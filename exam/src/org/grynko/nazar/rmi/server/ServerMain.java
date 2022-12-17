package org.grynko.nazar.rmi.server;

import lombok.SneakyThrows;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("==> Server started");

        Registry registry = LocateRegistry.createRegistry(8080);
        RmiServerRemote service = new RmiServer();
        registry.rebind("server", service);
    }

}
