package de.memorymage;

import de.memorymage.entity.Book;
import de.memorymage.entity.Bookshelf;
import de.memorymage.entity.Page;
import de.memorymage.strategy.queue;

public class BookshelfManager {

    public Bookshelf bookshelf = new Bookshelf();
    public Book currentBook;
    public Page currentPage;
    public queue currentQueue;


    public Bookshelf initializaBookshelfmanager() {
        Book book = new Book("Mathe");
        book.addPage(new Page("1 + 1?", "2"));
        book.addPage(new Page("9 + 10?", "21"));
        book.addPage(new Page("6 * 7?", "42"));
        book.addPage(new Page("30 + 37?", "67"));
        book.addPage(new Page("1 + 3?", "4"));
        book.addPage(new Page("18 + 6 ", "24"));
        bookshelf.addBook(book);

        Book book1 = new Book("bathe");
        book1.addPage(new Page("aaaaaaaaaaaaaa?", "2"));
        bookshelf.addBook(book1);

        Book book2 = new Book("aaaaa");
        book2.addPage(new Page("bbbbbbbbbbb?", "2"));
        bookshelf.addBook(book2);

        Book book3 = new Book("bbbb");
        book3.addPage(new Page("cccccccc?", "2"));
        bookshelf.addBook(book3);

        return bookshelf;


    }

}
