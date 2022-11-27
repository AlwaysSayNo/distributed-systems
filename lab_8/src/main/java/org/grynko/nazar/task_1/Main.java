package org.grynko.nazar.task_1;

import lombok.SneakyThrows;
import org.grynko.nazar.task_1.client.ClientMain;
import org.grynko.nazar.task_1.common.PBBuilder;
import org.grynko.nazar.task_1.server.ServerMain;

import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        threadStart();

    }

    @SneakyThrows
    private static void processStart(){
        ProcessBuilder serverBuilder = PBBuilder.build(
                ServerMain.class, Collections.emptyList(), Collections.emptyList());

        Process serverProcess = serverBuilder.inheritIO().start();

        ProcessBuilder clientBuilder = PBBuilder.build(
                ClientMain.class, Collections.emptyList(), Collections.emptyList());

        Process clientProcess = clientBuilder.inheritIO().start();


        int serverCode = serverProcess.waitFor();
        int clientCode = clientProcess.waitFor();

        System.out.println(serverCode);
        System.out.println(clientCode);
    }

    private static void threadStart() {
        Thread serverThread = new Thread(() -> ServerMain.main(new String[]{}));

        Thread clientThread = new Thread(() -> ClientMain.main(new String[]{}));

        serverThread.start();
        clientThread.start();
    }

}
