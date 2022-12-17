package org.grynko.nazar.socket.client;

import lombok.SneakyThrows;
import org.grynko.nazar.common.AttributeType;
import org.grynko.nazar.common.OperationType;
import org.grynko.nazar.common.Request;
import org.grynko.nazar.common.Response;
import org.grynko.nazar.model.Book;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SocketClient {

    private static final Integer PORT = 8080;

    @SneakyThrows
    public void start() {
        String host = InetAddress.getLocalHost().getHostName();
        boolean isStopped = false;

        try(
                Socket socket = new Socket(host, PORT);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Scanner scanner = new Scanner(System.in)
        ) {
            while(true){
                System.out.println(
                    "Choose option: \n" +
                            "1 - " + OperationType.FIND_BOOKS_BY_AUTHOR + "\n" +
                            "2 - " + OperationType.FIND_BOOKS_BY_PUBLISHER + "\n" +
                            "3 - " + OperationType.FIND_BOOKS_AFTER_YEAR + "\n" +
                            "4 - " + OperationType.CLOSE
                );

                int commandNumber = scanner.nextInt();
                scanner.nextLine();

                Request request = null;
                Map<String, Object> attributes = new HashMap<>();
                switch (commandNumber) {
                    case 1: {
                        System.out.println("Enter author: ");
                        String author = scanner.nextLine();

                        attributes.put(AttributeType.AUTHOR.name(), author);

                        request = new Request(OperationType.FIND_BOOKS_BY_AUTHOR, attributes);
                        break;
                    }
                    case 2: {
                        System.out.println("Enter publisher: ");
                        String publisher = scanner.nextLine();

                        attributes.put(AttributeType.PUBLISHER.name(), publisher);

                        request = new Request(OperationType.FIND_BOOKS_BY_PUBLISHER, attributes);
                        break;
                    }
                    case 3: {
                        System.out.println("Enter year: ");
                        Integer year = scanner.nextInt();

                        attributes.put(AttributeType.YEAR.name(), year);

                        request = new Request(OperationType.FIND_BOOKS_AFTER_YEAR, attributes);
                        break;
                    }
                    case 4: {
                        System.out.println("==> ClientMain stopped");
                        request = new Request(OperationType.CLOSE, attributes);
                        isStopped = true;
                        break;
                    }
                }

                out.writeObject(request);

                if(isStopped) break;

                System.out.println("Result: ");
                Response response = (Response) in.readObject();

                List<Book> books = response.getBooks();
                books.forEach(System.out::println);
            }
        }

    }

}
