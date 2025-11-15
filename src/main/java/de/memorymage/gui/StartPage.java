package de.memorymage.gui;

import de.memorymage.BookshelfManager;
import de.memorymage.entity.Book;
import de.memorymage.entity.Page;
import de.memorymage.strategy.queue;

import javax.swing.*;
import java.awt.*;

public class StartPage extends JPanel {

    public StartPage(MainWindow window, BookshelfManager manager) {

        setLayout(null);

        Image bg = new ImageIcon(getClass().getResource("/assets/bookshelf.png")).getImage();

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

        drawBooks(manager, window, background);

        JButton addBook = new JButton("+ Add Book");
        addBook.setBounds(20, 700, 150, 40);
        addBook.addActionListener(e -> openAddBookDialog(manager, window, background));
        background.add(addBook);
    }

    private void drawBooks(BookshelfManager manager, MainWindow window, JPanel background) {

        background.removeAll();

        Image bg = new ImageIcon(getClass().getResource("/assets/bookshelf.png")).getImage();

        background.setLayout(null);

        var books = manager.bookshelf.getBookshelf();

        for (int i = 0; i < books.size(); i++) {

            Book book = books.get(i);

            String iconPath = "/assets/books/" + book.getColor() + ".png";
            ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));

            JButton button = new JButton(icon);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setBounds(50 + i * 120, 200, 100, 250);

            int finalI = i;

            button.addActionListener(e -> {
                manager.currentBook = books.get(finalI);

                manager.currentQueue = new queue(manager.currentBook);
                manager.currentPage = manager.currentQueue.peek();

                window.openBookPage();
            });

            background.add(button);
        }

        JButton addBook = new JButton("+ Add Book");
        addBook.setBounds(20, 700, 150, 40);
        addBook.addActionListener(e -> openAddBookDialog(manager, window, background));
        background.add(addBook);

        background.revalidate();
        background.repaint();
    }

    private void openAddBookDialog(BookshelfManager manager, MainWindow window, JPanel background) {

        JTextField nameField = new JTextField();

        String[] colors = {"yellow", "pink", "red", "blue", "green"};
        JComboBox<String> colorBox = new JComboBox<>(colors);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Book name:"));
        panel.add(nameField);
        panel.add(new JLabel("Color:"));
        panel.add(colorBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add new book",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

            String title = nameField.getText().trim();
            String color = colorBox.getSelectedItem().toString();

            if (title.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty!");
                return;
            }

            // Neues Buch erstellen
            Book newBook = new Book(title);
            newBook.setColor(color);
            Page startPage = new Page("here your Question ^^", "here your Answer ^^");
            newBook.addPage(startPage);

            // WICHTIG → ins Regal schreiben
            manager.bookshelf.addBook(newBook);

            // Regal neu laden
            drawBooks(manager, window, background);
        }
    }
}
