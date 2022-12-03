package org.grynko.nazar.task_3.server.controller;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.grynko.nazar.task_3.common.util.Util;
import org.grynko.nazar.task_3.server.processor.RequestProcessor;
import org.springframework.stereotype.Controller;

@Controller
public class ServerFrontController {

    private final RequestProcessor requestProcessor;
    private final Channel channel;

    @SneakyThrows
    public ServerFrontController(RequestProcessor requestProcessor, Channel channel) {
        this.requestProcessor = requestProcessor;
        this.channel = channel;

        this.channel.queueDeclare("server", false, false, false, null);

    }

    @SneakyThrows
    public void processRequest() {
        this.channel.basicConsume(
            "server",
            true,
            (consumerTag, delivery) -> {
                try {
                    requestProcessor.getResponse(Util.deserializeObject(delivery.getBody()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            },
            consumerTag -> {});
    }
}
