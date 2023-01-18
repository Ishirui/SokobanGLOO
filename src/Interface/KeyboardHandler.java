package Interface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Controle.Controleur;
import Controle.Direction;

public class GestionClavier implements KeyListener {

    private Controleur controleur = new Controleur();

    @Override
    public void keyPressed(KeyEvent event) {
    }

    @Override
    public void keyReleased(KeyEvent event) {
        controleur.move(
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_UP -> Direction.UP;
                    case KeyEvent.VK_DOWN -> Direction.DOWN;
                    case KeyEvent.VK_RIGHT -> Direction.RIGHT;
                    case KeyEvent.VK_LEFT -> Direction.LEFT;
                    default -> null;
                });
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

}
