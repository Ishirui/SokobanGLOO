package View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

import Controller.Controller;
import Controller.InputToken;


public class KeyboardHandler implements KeyListener {
    private Controller controller;
    private Map<Integer, InputToken> keyMapping;

    public KeyboardHandler(Controller controller, Map<Integer, InputToken> keyMapping){
        this.controller = controller;
        this.keyMapping = keyMapping;
    }

    InputToken getToken(KeyEvent event){
        return keyMapping.getOrDefault(event.getKeyCode(), InputToken.NOTHING);
    }

    void handleToken(InputToken token){
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
        handleToken(getToken(event));
    }


    @Override
    public void keyPressed(KeyEvent event) {}
    @Override
    public void keyTyped(KeyEvent event) {}

}
