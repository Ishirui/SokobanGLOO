package Interface;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Modele.Box;
import Modele.Drawable;
import Modele.Floor;
import Modele.Wall;

public class SokobanTest implements Runnable {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new SokobanTest());
    }

    @Override
    public void run() {
        JFrame fenetre = new JFrame("Graphisme avec Swing");
        fenetre.setPreferredSize(new Dimension(3*128, 128));
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Drawable[] drawables = new Drawable[]{new Box(0,0), new Floor(1,0), new Wall(2,0)};

        DrawHandler drawHandler = null;
        try {
            drawHandler = new DrawHandler(drawables);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        fenetre.add(drawHandler);
        fenetre.pack();
        fenetre.setVisible(true);
    }

}
