package org.grynko.nazar.task_3.client.controller;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.grynko.nazar.task_3.common.constant.RequestType;
import org.grynko.nazar.task_3.common.request.ParameterizedRequest;
import org.grynko.nazar.task_3.common.util.Util;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class ClientFrontController {

    private final Channel channel;

    @SneakyThrows
    public ClientFrontController(Channel channel) {
        this.channel = channel;
        this.channel.queueDeclare("server", false, false, false, null);
    }

    public void process(String code, RequestType type, Map<String, Object> parameters) {
        ParameterizedRequest request = new ParameterizedRequest(code, type, parameters);

        process(request);
    }

    public void process(ParameterizedRequest request) {
        sendRequest(request);
    }

    @SneakyThrows
    private void sendRequest(ParameterizedRequest request) {
        this.channel.basicPublish("", "server", null, Util.serializeObject(request));;
    }

}
