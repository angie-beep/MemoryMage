package de.memorymage.gui;

import de.memorymage.BookshelfManager;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private final BookshelfManager manager;
    private JFrame frame;
    private JPanel cards;

    public MainWindow() {
        manager = new BookshelfManager();
        manager.initializaBookshelfmanager();

        frame = new JFrame("Memory Mage");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cards = new JPanel(new CardLayout());

        cards.add(new StartPage(this, manager), "start");

        frame.add(cards);
        frame.setVisible(true);
    }

    public void openBookPage(){
        cards.add(new BookPage(this, manager), "book");
        showPage("book");
    }

    public void showPage(String page){
        ((CardLayout) cards.getLayout()).show(cards, page);
    }
}
// hi hab dich lieb
