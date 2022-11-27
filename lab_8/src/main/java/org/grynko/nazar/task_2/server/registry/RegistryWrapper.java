package org.grynko.nazar.task_2.server.registry;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.rmi.Remote;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

@Component
public class RegistryWrapper {

    private final Map<String, Remote> remoteObjects;
    private final Registry registry;
    private boolean isClosed = false;

    public RegistryWrapper(Registry localRegistry) {
        this.remoteObjects = new HashMap<>();
        this.registry = localRegistry;
    }

    @SneakyThrows
    public void register(String name, Remote remote) {
        registry.rebind(name, remote);
        remoteObjects.put(name, remote);
    }

    @SneakyThrows
    public void stopServer() {
        if(!isClosed) return;

        for(String name: remoteObjects.keySet()) {
            registry.unbind(name);
        }

        UnicastRemoteObject.unexportObject(registry, true);

        isClosed = !isClosed;
    }

    @PreDestroy
    private void destroy() {
        System.out.println("==> Server#RegistryWrapper#PreDestroy");
        stopServer();
    }

}
