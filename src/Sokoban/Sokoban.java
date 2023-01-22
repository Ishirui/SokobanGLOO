package Sokoban;

import java.util.Map;
import java.awt.event.KeyEvent;
import static java.util.Map.entry; 


import Controle.InputToken;

public class Sokoban {

    //Static (parameter) fields
    public static Map<Integer, InputToken> levelKeyMapping = Map.ofEntries(
        entry(KeyEvent.VK_UP, InputToken.UP),
        entry(KeyEvent.VK_DOWN, InputToken.DOWN),
        entry(KeyEvent.VK_RIGHT, InputToken.RIGHT),
        entry(KeyEvent.VK_LEFT, InputToken.LEFT)
        );





    private static SokobanGame gameObject = new SokobanGame();

    public static void main(String[] args) {
        try {
            gameObject.goToLevel(-1);
        } catch (InvalidLevelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        gameObject.launch();
        
    }


    public static class InvalidLevelException extends Exception{
        public InvalidLevelException(String message){
            super(message);
        }
    };

    public static class SokobanRuntimeException extends Exception{
        public SokobanRuntimeException(String message){
            super(message);
        }
    };

}
