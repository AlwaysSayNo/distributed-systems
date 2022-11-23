package org.grynko.nazar.task_1.server;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.ServerSocket;

@Component
public class SingleSocketServer {

    private static ServerSocket server;

    public ServerSocket getServer() {
        if(server == null) {
            server = createServer();
        }
        return server;
    }

    @SneakyThrows
    public void closeConnection() {
        if(server != null) {
            server.close();
        }
    }

    @SneakyThrows
    private ServerSocket createServer() {
        int port = 8080;
        int maxConnections = 1;
        return new ServerSocket(port, maxConnections);
    }

    @PreDestroy
    private void preDestroy() {
        System.out.println("PreDestroy");
    }
}
