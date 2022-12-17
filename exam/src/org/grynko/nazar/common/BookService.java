package org.grynko.nazar.common;

import org.grynko.nazar.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {

    private static final List<Book> books = new ArrayList<>();

    static {
        books.addAll(
                Arrays.asList(
                        new Book(1, "Portions from a Wine-Stained Notebook", new String[]{"Charles Bukowski"}, "City Lights Publishers", 2008, 300, 24.5, "PUR binding"),
                        new Book(2, "The Catcher in the Rye by J.D. Salinger - Summary & Analysis", new String[]{"David Harrison", "Stephen Paul Aulridge Jr"}, "David Harrison", 2017, 123, 12.0, "PUR binding"),
                        new Book(3, "The Lives of a Cell: Notes of a Biology Watcher", new String[]{"Lewis Thomas", "Grover Gardner"}, " Blackstone Audio, Inc.", 1999, 220, 11.99, "Hardcover"),
                        new Book(4, "I Think Our Son Is Gay", new String[]{"Okura"}, "Square Enix Manga", 2022, 128, 9.99, "Hardcover"),
                        new Book(5, "Billy Herrington Notebook", new String[]{"Okura"}, "Square Enix Manga", 2021, 110, 6.24, "Hardcover")
                )
        );
    }

    public static List<Book> findBooksByAuthor(String author) {
        return books.stream()
                .filter(b -> Arrays.asList(b.getAuthors()).contains(author))
                .collect(Collectors.toList());
    }

    public static List<Book> findBooksByPublisher(String publisher) {
        return books.stream()
                .filter(b -> b.getPublisher().equals(publisher))
                .collect(Collectors.toList());
    }

    public static List<Book> findBooksAfterYear(int year) {
        return books.stream()
                .filter(b -> b.getYearOfPublication() > year)
                .collect(Collectors.toList());
    }

}
