package de.memorymage.gui;

import de.memorymage.BookshelfManager;
import de.memorymage.entity.Book;

import javax.swing.*;
import java.awt.*;

public class StartPage extends JPanel {

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
        background.setBounds(0, 0, 800, 600);
        add(background);

        ImageIcon bookIcon = new ImageIcon(getClass().getResource("/assets/book_red.png"));

        var books = manager.bookshelf.getBookshelf();

        for (int i = 0; i < books.size() ; i++) {
            int finalI = i;
            JButton bookButton = new JButton(bookIcon);
            bookButton.setBorderPainted(false);
            bookButton.setContentAreaFilled(false);
            bookButton.setFocusPainted(false);

            bookButton.setBounds(50 + i * 120, 200, 100, 250);

            bookButton.addActionListener(e -> {
                Book selected = books.get(finalI);
                manager.currentBook = selected;
                manager.currentPageIndex = 0;
                window.openBookPage();
            });

            background.add(bookButton);
        }
    }
}
