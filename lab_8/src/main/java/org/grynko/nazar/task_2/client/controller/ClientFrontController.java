package org.grynko.nazar.task_2.client.controller;

import lombok.SneakyThrows;
import org.grynko.nazar.task_2.common.constant.RequestType;
import org.grynko.nazar.task_2.common.request.ParameterizedRequest;
import org.grynko.nazar.task_2.common.response.ParameterizedResponse;
import org.grynko.nazar.task_2.server.controller.ServerFrontController;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class ClientFrontController {

    private final ServerFrontController serverFrontController;

    public ClientFrontController(ServerFrontController serverFrontController) {
        this.serverFrontController = serverFrontController;
    }

    public Map<String, Object> process(String code, RequestType type, Map<String, Object> parameters) {
        ParameterizedRequest request = new ParameterizedRequest(code, type, parameters);

        return process(request);
    }

    public Map<String, Object> process(ParameterizedRequest request) {
        ParameterizedResponse response = sendRequest(request);

        Map<String, Object> result;
        switch (response.getStatus()) {
            case OK: {
                result = response.getParameters();
                break;
            }

            case EXCEPTION: {
                throw new IllegalArgumentException();
            }

            default: {
                throw new IllegalStateException();
            }
        }

        return result;
    }

    @SneakyThrows
    private ParameterizedResponse sendRequest(ParameterizedRequest request) {
        return this.serverFrontController.processRequest(request);
    }

}
