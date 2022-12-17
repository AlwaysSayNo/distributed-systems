package org.grynko.nazar.rmi.server;

import org.grynko.nazar.common.BookService;
import org.grynko.nazar.model.Book;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RmiServer extends UnicastRemoteObject implements RmiServerRemote {


    protected RmiServer() throws RemoteException {
    }

    @Override
    public List<Book> findBooksByAuthor(String author) throws RemoteException {
        return BookService.findBooksByAuthor(author);
    }

    @Override
    public List<Book> findBooksByPublisher(String publisher) throws RemoteException {
        return BookService.findBooksByPublisher(publisher);
    }

    @Override
    public List<Book> findBooksAfterYear(int year) throws RemoteException {
        return BookService.findBooksAfterYear(year);
    }
}
