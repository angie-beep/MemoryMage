package de.memorymage;

import de.memorymage.entity.Bookshelf;
import de.memorymage.strategy.queue;

public class Main {
    public static void main(String[] args) {

    BookshelfManager bookshelfManager = new BookshelfManager();
    Bookshelf bookshelf = bookshelfManager.initializaBookshelfmanager();


    queue queue = new queue(bookshelf.getBookshelf().get(0));

    System.out.println(queue.toString());
   }
}