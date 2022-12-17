package org.grynko.nazar.rmi.client;

import lombok.SneakyThrows;
import org.grynko.nazar.common.AttributeType;
import org.grynko.nazar.common.OperationType;
import org.grynko.nazar.common.Request;
import org.grynko.nazar.common.Response;
import org.grynko.nazar.model.Book;
import org.grynko.nazar.rmi.server.RmiServerRemote;

import java.rmi.Naming;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RmiClient {

    @SneakyThrows
    public static void start(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            RmiServerRemote server = (RmiServerRemote) Naming.lookup("//localhost:8080/server");
            boolean isStopped = false;

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

                List<Book> books = null;
                switch (commandNumber) {
                    case 1: {
                        System.out.println("Enter author: ");
                        String author = scanner.nextLine();

                        books = server.findBooksByAuthor(author);
                        break;
                    }
                    case 2: {
                        System.out.println("Enter publisher: ");
                        String publisher = scanner.nextLine();

                        books = server.findBooksByPublisher(publisher);
                        break;
                    }
                    case 3: {
                        System.out.println("Enter year: ");
                        int year = scanner.nextInt();

                        books = server.findBooksAfterYear(year);
                        break;
                    }
                    case 4: {
                        System.out.println("==> ClientMain stopped");
                        isStopped = true;
                        break;
                    }
                }

                if(isStopped) break;

                System.out.println("Result: ");

                books.forEach(System.out::println);
            }
        }
    }

}
