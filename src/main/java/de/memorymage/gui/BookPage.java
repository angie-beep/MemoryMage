package de.memorymage.gui;

import de.memorymage.BookshelfManager;
import de.memorymage.entity.Page;

import javax.swing.*;
import java.awt.*;

public class BookPage extends JPanel {

    private JLabel questionLabel;
    private JLabel answerLabel;

    public BookPage(MainWindow window, BookshelfManager manager) {

        setLayout(new BorderLayout());

        JLabel wizard = new JLabel(new ImageIcon(getClass().getResource("/assets/wizard_default.png")));
        JPanel left = new JPanel();
        left.add(wizard);

        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        JLabel openbook = new JLabel(new ImageIcon(getClass().getResource("/assets/open_book.png")));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 22));
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        answerLabel = new JLabel("???");
        answerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        answerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        answerLabel.setVisible(false);

        JButton btnRight = new JButton("Right");
        JButton btnWrong = new JButton("Wrong");

        btnRight.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnWrong.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnRight.addActionListener(e -> {
            nextPage(manager);
        });

        btnWrong.addActionListener(e -> {
            nextPage(manager);
        });

        right.add(openbook);
        right.add(questionLabel);
        right.add(answerLabel);
        right.add(Box.createVerticalStrut(20));
        right.add(btnRight);
        right.add(Box.createVerticalStrut(10));
        right.add(btnWrong);

        add(left, BorderLayout.WEST);
        add(right, BorderLayout.CENTER);

        updatePage(manager);
    }

    private void updatePage(BookshelfManager manager) {
        Page p = manager.currentBook.getPages().get(manager.currentPageIndex);
        questionLabel.setText("Q: " + p.getQuestion());
        answerLabel.setText("A: " + p.getAnswer());
    }

    private void nextPage(BookshelfManager manager) {
        if (manager.currentPageIndex < manager.currentBook.getPages().size() - 1) {
            manager.currentPageIndex++;
            updatePage(manager);
        }
    }
}
