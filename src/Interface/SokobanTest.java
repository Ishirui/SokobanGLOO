package Interface;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SokobanTest implements Runnable {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new SokobanTest());
    }

    @Override
    public void run() {
        JFrame fenetre = new JFrame("Graphisme avec Swing");
        fenetre.setPreferredSize(new Dimension(570, 292 + 20));
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dessin monDessin = new Dessin();
        fenetre.add(monDessin);
        fenetre.addKeyListener(new GestionClavier(monDessin));
        fenetre.pack();
        fenetre.setVisible(true);
    }

}
