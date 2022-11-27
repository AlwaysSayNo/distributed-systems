package org.grynko.nazar.task_1.server.controller;

import lombok.SneakyThrows;
import org.grynko.nazar.task_1.common.request.ParameterizedRequest;
import org.grynko.nazar.task_1.common.response.ParameterizedResponse;
import org.grynko.nazar.task_1.server.processor.RequestProcessor;
import org.springframework.stereotype.Controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Controller
public class ServerFrontController {

    private final Socket socket;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private final RequestProcessor requestProcessor;

    @SneakyThrows
    public ServerFrontController(Socket socket, RequestProcessor requestProcessor) {
        this.socket = socket;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.requestProcessor = requestProcessor;
    }


/*    @SneakyThrows
    public ServerFrontController(ServerSocket serverSocket) {
        this.socket = null;// serverSocket.accept();
        this.in = null; //new ObjectInputStream(socket.getInputStream());
        this.out =  null; //new ObjectOutputStream(socket.getOutputStream());
    }*/

    public void process() {
        ParameterizedRequest request = receiveRequest();

        ParameterizedResponse response = requestProcessor.getResponse(request);

        sendResponse(response);
    }

    @SneakyThrows
    private ParameterizedRequest receiveRequest() {
        return (ParameterizedRequest) in.readObject();
    }

    @SneakyThrows
    private void sendResponse(ParameterizedResponse response) {
        out.writeObject(response);
    }

}
