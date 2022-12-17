package org.grynko.nazar.socket.server;

import lombok.SneakyThrows;
import org.grynko.nazar.common.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class RequestProcessor {

    private final SocketServer server;
    private final Socket client;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;


    @SneakyThrows
    public RequestProcessor(SocketServer server, Socket client) {
        this.server = server;
        this.client = client;
        this.in = new ObjectInputStream(client.getInputStream());
        this.out = new ObjectOutputStream(client.getOutputStream());
    }


    @SneakyThrows
    public void process() {
        Request request = receiveRequest();
        OperationType operationType = request.getOperationType();

        System.out.println(client.getInetAddress().getHostAddress() + ": " + operationType.name());

        Response response = null;
        Map<String, Object> attributes = request.getAttributes();

        switch (operationType) {
            case FIND_BOOKS_BY_AUTHOR: {
                String author = (String) attributes.get(AttributeType.AUTHOR.name());
                response = new Response(BookService.findBooksByAuthor(author));
                break;
            }
            case FIND_BOOKS_BY_PUBLISHER: {
                String publisher = (String) attributes.get(AttributeType.PUBLISHER.name());
                response = new Response(BookService.findBooksByPublisher(publisher));
                break;
            }
            case FIND_BOOKS_AFTER_YEAR: {
                Integer year = (Integer) attributes.get(AttributeType.YEAR.name());
                response = new Response(BookService.findBooksAfterYear(year));
                break;
            }
            case CLOSE: {
                server.stop();
                break;
            }
        }

        sendResponse(response);
    }

    @SneakyThrows
    private Request receiveRequest() {
        return (Request) in.readObject();
    }

    @SneakyThrows
    private void sendResponse(Response response) {
        out.writeObject(response);
    }

}
