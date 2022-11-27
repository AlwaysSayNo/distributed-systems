package org.grynko.nazar.task_1.client.socket;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.Socket;

@Component
public class SingleSocketPool {

    @Value("${server.port}")
    Integer port;
    @Value("${server.ip.address}")
    String IPAddress;

    private static Socket socket;

    public Socket getSocket() {
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
    private Socket createSocket() {
        return new Socket(IPAddress, port);
    }

    @SneakyThrows
    @PreDestroy
    private void destroy() {
        System.out.println("==> Client#SingleSocketPool#PreDestroy");
        closeSocket();
    }

}
