package Interface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

import Controle.LogicController;
import Controle.InputToken;
import Sokoban.Sokoban.SokobanRuntimeException;


public class KeyboardHandler implements KeyListener {
    private LogicController logicController;
    private WindowController windowController;
    private Map<Integer, InputToken> keyMapping;

    public KeyboardHandler(LogicController controller, WindowController windowController, Map<Integer, InputToken> keyMapping){
        this.logicController = controller;
        this.windowController = windowController;
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
            this.logicController.takeGameAction(token);
        } catch (SokobanRuntimeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        windowController.refresh();

    }

    @Override
    public void keyTyped(KeyEvent event) {}

}
