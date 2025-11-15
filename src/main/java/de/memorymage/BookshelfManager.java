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
        Book book1 = new Book("Magic Spells");
        book1.setColor("blue");
        book1.addPage(new Page("whats the spell for water?", "Aqua"));
        book1.addPage(new Page("whats the spell for fire?", "Ignis"));
        book1.addPage(new Page("whats the spell for earth?", "Terra"));
        book1.addPage(new Page("whats the spell for air?", "Ventus"));
        book1.addPage(new Page("whats the spell for light?", "Lux"));
        book1.addPage(new Page("whats the spell for shadow?", "Umbra"));
        bookshelf.addBook(book1);

        Book book2 = new Book("Math");
        book2.setColor("blue");
        book2.addPage(new Page("9 + 10?", "21"));
        book2.addPage(new Page("1 + 1?", "2"));
        book2.addPage(new Page("2 - 2?", "0"));
        book2.addPage(new Page("6 + 7?", "13"));
        book2.addPage(new Page("3 * 3?", "9"));
        book2.addPage(new Page("21 % 7?", "0"));
        book2.addPage(new Page("21 / 7", "3"));
        bookshelf.addBook(book2);


        return bookshelf;
    }

}
