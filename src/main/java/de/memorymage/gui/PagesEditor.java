package de.memorymage.gui;

import de.memorymage.BookshelfManager;
import de.memorymage.entity.Book;
import de.memorymage.entity.Page;
import de.memorymage.strategy.queue;

import javax.swing.*;
import java.awt.*;

public class PagesEditor extends JFrame {

    private Book book;
    private BookshelfManager manager;
    private BookPage bookPage;
    private MainWindow window;     // <-- neu
    private DefaultListModel<Page> listModel;
    private JList<Page> list;

    public PagesEditor(Book book, BookshelfManager manager, BookPage bookPage, MainWindow window) {
        this.book = book;
        this.manager = manager;
        this.bookPage = bookPage;
        this.window = window;

        setTitle("Pages – " + book.getTitle());
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        listModel = new DefaultListModel<>();
        for (Page p : book.getPages()) {
            listModel.addElement(p);
        }

        list = new JList<>(listModel);
        list.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(list);

        JButton btnAdd = new JButton("Add Page");
        JButton btnDelete = new JButton("Delete Selected");
        JButton btnSave = new JButton("Save & Close");

        btnAdd.addActionListener(e -> addPageDialog());
        btnDelete.addActionListener(e -> deleteSelectedPage());

        // ----- SAVE & EXIT FIX -----
        btnSave.addActionListener(e -> {

            // Queue neu erstellen
            manager.currentQueue = new queue(book);

            if (!manager.currentQueue.isEmpty()) {
                manager.currentPage = manager.currentQueue.peek();
            }

            dispose();

            // === WICHTIG ===
            // Verhalten wie: Buch im Bücherregal erneut anklicken
            window.showPage("start");
            window.openBookPage();
        });

        JPanel bottom = new JPanel(new FlowLayout());
        bottom.add(btnAdd);
        bottom.add(btnDelete);
        bottom.add(btnSave);

        add(scrollPane, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }


    private void addPageDialog() {
        JTextField qField = new JTextField();
        JTextField aField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Question:"));
        panel.add(qField);
        panel.add(new JLabel("Answer:"));
        panel.add(aField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add Page",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {

            String question = qField.getText().trim();
            String answer = aField.getText().trim();

            if (question.isEmpty() || answer.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Question and answer cannot be empty.");
                return;
            }

            Page newPage = new Page(question, answer);
            book.addPage(newPage);
            listModel.addElement(newPage);
        }
    }


    private void deleteSelectedPage() {
        Page selected = list.getSelectedValue();

        if (selected == null) {
            JOptionPane.showMessageDialog(this, "No page selected.");
            return;
        }

        int result = JOptionPane.showConfirmDialog(
                this,
                "Delete this page?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
        );

        if (result == JOptionPane.YES_OPTION) {
            book.removePage(selected);
            listModel.removeElement(selected);
        }
    }
}
