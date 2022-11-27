package org.grynko.nazar.task_2.server.controller;

import org.grynko.nazar.task_2.common.request.ParameterizedRequest;
import org.grynko.nazar.task_2.common.response.ParameterizedResponse;
import org.grynko.nazar.task_2.server.processor.RequestProcessor;
import org.springframework.stereotype.Controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@Controller
public class ServerFrontControllerImpl extends UnicastRemoteObject implements ServerFrontController {

    private final RequestProcessor requestProcessor;

    public ServerFrontControllerImpl(RequestProcessor requestProcessor) throws RemoteException {
        super();
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ParameterizedResponse processRequest(ParameterizedRequest request) throws RemoteException {
        return requestProcessor.getResponse(request);
    }
}
