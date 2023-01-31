package View;

import static java.util.Map.entry;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

import Controller.Controller;
import Sokoban.SokobanExceptions.InvalidLevelException;
import Sokoban.SokobanExceptions.SokobanRuntimeException;


public class KeyboardHandler implements KeyListener {
    private Controller controller;
    
    

    public KeyboardHandler(Controller controller){
        this.controller = controller;
    }

    InputToken getToken(KeyEvent event){
        Map<Integer, InputToken> keyMapping = Map.ofEntries(
        entry(KeyEvent.VK_UP, InputToken.UP),
        entry(KeyEvent.VK_DOWN, InputToken.DOWN),
        entry(KeyEvent.VK_RIGHT, InputToken.RIGHT),
        entry(KeyEvent.VK_LEFT, InputToken.LEFT),
        entry(KeyEvent.VK_R, InputToken.RESET)
    );
        
        return keyMapping.getOrDefault(event.getKeyCode(), InputToken.NOTHING);
    }

    void handleToken(InputToken token) throws InvalidLevelException, SokobanRuntimeException{
        switch(token){
            case RESET:
                controller.resetLevel();
                break;
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
                controller.doMove(token);
                break;
            default:
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        try {
            handleToken(getToken(event));
        } catch (InvalidLevelException | SokobanRuntimeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public void keyPressed(KeyEvent event) {}
    @Override
    public void keyTyped(KeyEvent event) {}

}
