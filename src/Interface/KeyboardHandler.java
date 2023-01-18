package Interface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Controle.Direction;

public class KeyboardHandler implements KeyListener {
    private Direction lastDir;
    private boolean hasUnaccountedChanges = false;

    @Override
    public void keyPressed(KeyEvent event) {}

    @Override
    public void keyReleased(KeyEvent event) {
        if (hasUnaccountedChanges) return; //Don't want to accidentally overwrite a command that wasn't taken into account

        Direction tempDirection =   switch (event.getKeyCode()) {
                                        case KeyEvent.VK_UP -> Direction.UP;
                                        case KeyEvent.VK_DOWN -> Direction.DOWN;
                                        case KeyEvent.VK_RIGHT -> Direction.RIGHT;
                                        case KeyEvent.VK_LEFT -> Direction.LEFT;
                                        default -> null;
                                    };

        if(tempDirection != null){
            lastDir = tempDirection;
            hasUnaccountedChanges = true;
        }
    }

    public Direction getChanges(){
        if (!hasUnaccountedChanges) return null;
        
        hasUnaccountedChanges = false;
        return lastDir;
    }

    @Override
    public void keyTyped(KeyEvent event) {}

}
