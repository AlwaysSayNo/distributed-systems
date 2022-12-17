package org.grynko.nazar.rmi.server;

import org.grynko.nazar.model.Book;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RmiServerRemote extends Remote {

    List<Book> findBooksByAuthor(String author) throws RemoteException;

    List<Book> findBooksByPublisher(String publisher) throws RemoteException;

    List<Book> findBooksAfterYear(int year) throws RemoteException;

}
