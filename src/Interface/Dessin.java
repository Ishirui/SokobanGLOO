package Interface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Dessin extends JPanel {

    private Image image;
    private Color couleur;

    public Dessin() {
        super();

        couleur = Color.RED;

        try {
            image = ImageIO.read(new File("logo-CS-blanc.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(couleur);
        g.fillRect(0, 0, 570, 292);
        g.drawImage(image, 0, 0, null);
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color c) {
        couleur = c;
        repaint();
    }
}
