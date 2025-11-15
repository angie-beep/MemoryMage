package de.memorymage;

import de.memorymage.entity.Book;
import de.memorymage.entity.Bookshelf;
import de.memorymage.entity.Page;

public class BookshelfManager {

    public Bookshelf bookshelf = new Bookshelf();
    public Book currentBook;
    public int currentPageIndex = 0;



    public void select_currentBook(Book book){
        currentBook = book;
    }


    public Bookshelf initializaBookshelfmanager(){
        Book book = new Book("Mathe");
        book.addPage(new Page("1 + 1?","2"));
        book.addPage(new Page("9 + 10?","21"));
        book.addPage(new Page("6 * 7?","42"));
        book.addPage(new Page("30 + 37?","67"));
        book.addPage(new Page("1 + 3?","4"));
        book.addPage(new Page("18 + 6 ","24"));
        bookshelf.addBook(book);
        return bookshelf;
    }





}
