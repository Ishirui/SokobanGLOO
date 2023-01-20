package Interface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

import Controle.Controller;
import Controle.InputToken;
import Sokoban.Sokoban.SokobanRuntimeException;


public class KeyboardHandler implements KeyListener {
    private Controller controller;
    private Map<Integer, InputToken> keyMapping;

    public KeyboardHandler(Controller controller, Map<Integer, InputToken> keyMapping){
        this.controller = controller;
        this.keyMapping = keyMapping;
    }


    @Override
    public void keyPressed(KeyEvent event) {}

    @Override
    public void keyReleased(KeyEvent event) {
        InputToken token = keyMapping.getOrDefault(event.getKeyCode(), InputToken.NOTHING);

        System.out.println(token);


        if(token == InputToken.NOTHING) return;
        
        try {
            this.controller.onInput(token);
        } catch (SokobanRuntimeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent event) {}

}
