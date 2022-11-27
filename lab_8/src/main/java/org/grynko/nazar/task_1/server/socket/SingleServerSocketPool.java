package org.grynko.nazar.task_1.server.socket;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class SingleServerSocketPool {

    @Value("${server.port}")
    int port = 8080;
    @Value("${max.sockets.amount}")
    int maxSocketsAmount = 1;

    private static ServerSocket server;
    private static Socket socket;

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

    public Socket getSocket() {
        if(server == null) {
            getServer();
        }

        if(socket == null) {
            socket = createSocket();
        }

        return socket;
    }

    @SneakyThrows
    public void closeSocket() {
        if(socket != null) {
            socket.close();
        }
    }

    @SneakyThrows
    private ServerSocket createServer() {
        return new ServerSocket(port, maxSocketsAmount);
    }

    @SneakyThrows
    private Socket createSocket() {
        return server.accept();
    }

    @SneakyThrows
    @PreDestroy
    private void destroy() {
        System.out.println("==> Server#SingleServerSocketPool#PreDestroy");
        closeServer();
        closeSocket();
    }

}
