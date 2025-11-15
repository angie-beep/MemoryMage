package de.memorymage.entity;
import java.util.ArrayList;

public class Bookshelf {

    ArrayList<Book> bookshelf = new ArrayList<>();

    public ArrayList<Book> getBookshelf() {
        return bookshelf;
    }

    public void addBook(Book book) {
        bookshelf.add(book);
    }

    public void removeBook(Book book) {
        bookshelf.remove(book);
    }


}
