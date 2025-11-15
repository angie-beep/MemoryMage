package de.memorymage.strategy;

import java.util.ArrayList;
import de.memorymage.entity.Book;
import de.memorymage.entity.Page;

public class queue {
    ArrayList<Page> queue = new ArrayList<>();

    public queue(Book book){
        queue.addAll(book.getPages());

    }

    // yes
    public void pop (){
        queue.remove(0);
    }

    // fail
    public void fail(){
        Page current = queue.get(0);
        queue.add(current);
        queue.remove(0);
    }

    public String toString(){
        String output = "";
        for(Page page : queue){
            output += page.toString() + "\n";
        }
        return output;
    }
}
