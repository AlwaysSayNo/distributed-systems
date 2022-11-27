package org.grynko.nazar.task_2.server.controller;

import org.grynko.nazar.task_2.common.request.ParameterizedRequest;
import org.grynko.nazar.task_2.common.response.ParameterizedResponse;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerFrontController extends Remote {

    ParameterizedResponse processRequest(ParameterizedRequest request) throws RemoteException;;

}
