package org.grynko.nazar.model;

import java.io.Serializable;
import java.util.Arrays;

public class Book  implements Serializable {

    private int id;
    private String title;
    private String[] authors;
    private String publisher;
    private int yearOfPublication;
    private int pagesNumber;
    private double price;
    private String typeOfBinding;

    public Book() {
    }

    public Book(int id, String title, String[] authors, String publisher, int yearOfPublication, int pagesNumber, double price, String typeOfBinding) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.yearOfPublication = yearOfPublication;
        this.pagesNumber = pagesNumber;
        this.price = price;
        this.typeOfBinding = typeOfBinding;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTypeOfBinding() {
        return typeOfBinding;
    }

    public void setTypeOfBinding(String typeOfBinding) {
        this.typeOfBinding = typeOfBinding;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + Arrays.toString(authors) +
                ", publisher='" + publisher + '\'' +
                ", yearOfPublication='" + yearOfPublication + '\'' +
                ", pagesNumber=" + pagesNumber +
                ", price=" + price +
                ", typeOfBinding='" + typeOfBinding + '\'' +
                '}';
    }
}
