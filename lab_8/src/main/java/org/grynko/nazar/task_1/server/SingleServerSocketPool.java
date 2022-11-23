package org.grynko.nazar.task_1.server;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;

@Component
public class SingleServerSocketPool {

    @Value("${server.port}")
    int port = 8080;
    @Value("${max.sockets.amount}")
    int maxSocketsAmount = 1;
    private static ServerSocket server;

    public ServerSocket getServer() {
        if(server == null) {
            server = createServer();
        }
        return server;
    }

    @SneakyThrows
    public void closeServer() {
        if(server != null) {
            server.close();
        }
    }

    @SneakyThrows
    private ServerSocket createServer() {
        return new ServerSocket(port, maxSocketsAmount);
    }

}
