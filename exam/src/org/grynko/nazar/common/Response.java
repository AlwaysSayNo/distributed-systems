package org.grynko.nazar.common;

import org.grynko.nazar.model.Book;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {

    private final List<Book> books;

    public Response(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }
}
