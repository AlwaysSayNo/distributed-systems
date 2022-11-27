package org.grynko.nazar.task_2;

import lombok.SneakyThrows;
import org.grynko.nazar.task_2.client.ClientMain;
import org.grynko.nazar.task_2.server.ServerMain;

public class Main {

    public static void main(String[] args) {
        threadStart();
    }

    @SneakyThrows
    private static void threadStart() {
        Thread serverThread = new Thread(() -> ServerMain.main(new String[]{}));

        Thread clientThread = new Thread(() -> ClientMain.main(new String[]{}));

        serverThread.start();
        clientThread.start();

        serverThread.join();
        clientThread.join();
    }

}
