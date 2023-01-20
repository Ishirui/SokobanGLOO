package Interface;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Modele.Box;
import Modele.Drawable;
import Modele.Floor;
import Modele.Player;
import Modele.Target;
import Modele.Wall;

public class SokobanTest {

    public static void main(String[] args) {
        Drawable[] testElements = { new Wall(0,0), new Wall(1, 0), new Wall(2,0), new Wall(3, 0),
                                new Wall(0,1), new Box(1, 1), new Floor(2, 1), new Wall(3, 1),
                                new Wall(0,2), new Floor(1, 2), new Player(2, 2), new Wall(3, 2),
                                new Wall(0,3), new Wall(1, 3), new Wall(2,3), new Wall(3, 3),
                                new Target(2,1)};

        SwingUtilities.invokeLater(new LevelWindowController(testElements, 48, "TestLevel"));
    }

}
