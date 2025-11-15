package de.memorymage.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BookshelfView extends JPanel {

    private Image shelfImage;
    private List<Image> books;

    public BookshelfView(List<Image> books) {
        this.books = books;

        shelfImage = new ImageIcon(getClass().getResource("/assets/bookshelf.png"))
                .getImage();

        setLayout(null);
        setPreferredSize(new Dimension(shelfImage.getWidth(null), shelfImage.getHeight(null)));

        placeBooks();
    }

    private void placeBooks() {

        int xStart = 20;   // linker Rand
        int spacing = 8;   // minimaler Abstand zwischen Büchern
        int bookWidth = 60;
        int bookHeight = 90;

        // EXAKTE BOTTOM-Y-POSITIONEN DER HOLZBRETTER
        int[] shelfBottom = {
                79,   // Regalbrett 1 (nach Slot 1)
                170,  // Regalbrett 2
                265   // Regalbrett 3
        };

        int shelf = 0;
        int x = xStart;

        for (Image img : books) {

            if (shelf >= 3) break;

            Image scaled = img.getScaledInstance(bookWidth, bookHeight, Image.SCALE_SMOOTH);
            JLabel b = new JLabel(new ImageIcon(scaled));

            // Stelle das Buch exakt auf das Brett
            int y = shelfBottom[shelf] - bookHeight;

            b.setBounds(x, y, bookWidth, bookHeight);
            add(b);

            x += bookWidth + spacing;

            // Wenn Regalbrett voll → nächstes Brett
            if (x + bookWidth >= shelfImage.getWidth(null)) {
                shelf++;
                x = xStart;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(shelfImage, 0, 0, getWidth(), getHeight(), this);
    }
}
