package de.memorymage.gui;

import de.memorymage.BookshelfManager;
import de.memorymage.entity.Book;
import de.memorymage.strategy.queue;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class StartPage extends JPanel {
    Random rand = new Random();

    public StartPage(MainWindow window, BookshelfManager manager) {

        setLayout(null);

        Image bg = new ImageIcon(getClass().getResource("/assets/bookshelf.png")).getImage();

        JPanel self = this;

        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        background.setLayout(null);
        background.setBounds(0, 0, 1200, 800);
        add(background);

        int numberOfBooks = 5;
        int randomBookIndex;
        var books = manager.bookshelf.getBookshelf();

        for (int i = 0; i < books.size() ; i++) {

            randomBookIndex = rand.nextInt(numberOfBooks) + 1;
            String path = "/assets/books/book" + randomBookIndex + ".png";
            ImageIcon bookIcon = new ImageIcon(getClass().getResource(path));
            int finalI = i;
            JButton bookButton = new JButton(bookIcon);
            bookButton.setBorderPainted(false);
            bookButton.setContentAreaFilled(false);
            bookButton.setFocusPainted(false);

            bookButton.setBounds(50 + i * 120, 200, 100, 250);

            bookButton.addActionListener(e -> {
                Book selected = books.get(finalI);
                manager.currentBook = selected;

                manager.currentQueue = new queue(selected);
                manager.currentPage = manager.currentQueue.peek();

                window.openBookPage();

            });

            background.add(bookButton);
        }
    }
}
