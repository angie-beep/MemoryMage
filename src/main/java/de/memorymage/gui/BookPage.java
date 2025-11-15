package de.memorymage.gui;

import de.memorymage.BookshelfManager;
import de.memorymage.entity.Page;
import de.memorymage.strategy.queue;

import javax.swing.*;
import java.awt.*;

public class BookPage extends JPanel {

    private JLabel questionLabel;
    private JLabel answerLabel;
    private JLabel wizard;
    private queue queue;

    public BookPage(MainWindow window, BookshelfManager manager) {

        queue = manager.currentQueue;

        setLayout(new BorderLayout());

        // ===== WALL BACKGROUND PANEL =====
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image bg = new ImageIcon(getClass().getResource("/assets/wall.jpeg")).getImage();
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        add(backgroundPanel);

        JLabel titleLabel = new JLabel(manager.currentBook.getTitle(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 32));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        backgroundPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setPreferredSize(new Dimension(400, 800));
        left.setOpaque(false);

        wizard = new JLabel(scaleIcon("/assets/wizard_default.png", 550, 550));
        wizard.setAlignmentX(Component.CENTER_ALIGNMENT);

        left.add(Box.createVerticalStrut(40));
        left.add(Box.createHorizontalStrut(60));
        left.add(wizard);

        Image bookImage = new ImageIcon(getClass().getResource("/assets/open_book.png"))
                .getImage()
                .getScaledInstance(700, 450, Image.SCALE_SMOOTH);

        JPanel bookPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bookImage, 0, 0, getWidth(), 450, this);
            }
        };
        bookPanel.setLayout(null);
        bookPanel.setPreferredSize(new Dimension(800, 800));
        bookPanel.setOpaque(false);

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        questionLabel.setBounds(80, 120, 250, 200);

        answerLabel = new JLabel("", SwingConstants.CENTER);
        answerLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        answerLabel.setBounds(380, 120, 250, 200);
        answerLabel.setVisible(false);

        bookPanel.add(questionLabel);
        bookPanel.add(answerLabel);

        JPanel buttonBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        buttonBar.setOpaque(false);

        JButton btnBack   = makeIconButton("/assets/back_button.png",   140, 55);
        JButton btnReveal = makeIconButton("/assets/reveal_button.png", 140, 55);
        JButton btnRight  = makeIconButton("/assets/right_button.png",  140, 55);
        JButton btnWrong  = makeIconButton("/assets/wrong_button.png",  140, 55);

        btnBack.addActionListener(e -> window.showPage("start"));
        btnReveal.addActionListener(e -> answerLabel.setVisible(true));

        btnRight.addActionListener(e -> {
            flashWizard("/assets/wizard_right.png", 1000);
            queue.pop();
            nextPage(manager);
        });

        btnWrong.addActionListener(e -> {
            flashWizard("/assets/wizard_wrong.png", 1000);
            queue.fail();
            nextPage(manager);
        });

        JButton btnPages = new JButton("Edit Pages");
        btnPages.addActionListener(e -> new PagesEditor(manager.currentBook, manager, this, window));

        JButton btnDeleteBook = new JButton("Delete Book");
        btnDeleteBook.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "You sure u wanna delete dat book?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
            );
            if (result == JOptionPane.YES_OPTION) {
                manager.bookshelf.getBookshelf().remove(manager.currentBook);
                manager.currentBook = null;
                manager.currentPage = null;
                manager.currentQueue = null;
                window.showPage("start");
            }
        });

        buttonBar.add(btnDeleteBook);
        buttonBar.add(btnPages);
        buttonBar.add(btnBack);
        buttonBar.add(btnReveal);
        buttonBar.add(btnRight);
        buttonBar.add(btnWrong);

        backgroundPanel.add(left, BorderLayout.WEST);
        backgroundPanel.add(bookPanel, BorderLayout.CENTER);
        backgroundPanel.add(buttonBar, BorderLayout.SOUTH);

        updatePage(manager);
    }

    private void flashWizard(String imgPath, int durationMs) {
        wizard.setIcon(scaleIcon(imgPath, 550, 550));

        new javax.swing.Timer(durationMs, e -> {
            wizard.setIcon(scaleIcon("/assets/wizard_default.png", 550, 550));
            ((javax.swing.Timer) e.getSource()).stop();
        }).start();
    }

    public void updatePage(BookshelfManager manager) {
        Page p = manager.currentPage;
        questionLabel.setText("<html><center>" + p.getQuestion() + "</center></html>");
        answerLabel.setText("<html><center>" + p.getAnswer() + "</center></html>");
        answerLabel.setVisible(false);
    }

    private void nextPage(BookshelfManager manager) {
        if (!queue.isEmpty()) {
            manager.currentPage = queue.peek();
            updatePage(manager);
        }
    }

    private ImageIcon scaleIcon(String path, int w, int h) {
        Image img = new ImageIcon(getClass().getResource(path)).getImage();
        Image scaled = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    private JButton makeIconButton(String path, int w, int h) {
        Image img = new ImageIcon(getClass().getResource(path)).getImage();
        Image scaled = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);

        JButton btn = new JButton(new ImageIcon(scaled));
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);

        return btn;
    }
}
