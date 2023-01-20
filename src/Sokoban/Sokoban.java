package Sokoban;

import java.util.HashMap;
import java.util.Map;
import java.awt.event.KeyEvent;
import static java.util.Map.entry; 

import javax.swing.SwingUtilities;

import Controle.InputToken;
import Controle.GameController;
import Interface.KeyboardHandler;
import Interface.LevelWindowController;
import Modele.BaseObject;
import Modele.Drawable;

public class Sokoban {

    //Static (parameter) fields
    private static Map<Integer, InputToken> levelKeyMapping = Map.ofEntries(
        entry(KeyEvent.VK_UP, InputToken.UP),
        entry(KeyEvent.VK_DOWN, InputToken.DOWN),
        entry(KeyEvent.VK_RIGHT, InputToken.RIGHT),
        entry(KeyEvent.VK_LEFT, InputToken.LEFT)
        );

    //Dynamic fields
    private static int currentLevelNumber;

    private static GameController currentLevelController;
    private static LevelWindowController currentLevelWindowController;
    private static KeyboardHandler currentKeyboardHandler;

    private static LevelDefinitionLoader levelLoader = new LevelDefinitionLoader();

    static void goToLevel(int levelNumber) throws InvalidLevelException{
        Sokoban.currentLevelNumber = levelNumber;
        BaseObject[] levelObjects = levelLoader.getLevelObjects(levelNumber);

        //Generate game controller for this level
        currentLevelController = new GameController(levelObjects);

        //Generate keyboard controller with the appropriate mapping, and attach it to the game controller
        currentKeyboardHandler = new KeyboardHandler(currentLevelController, levelKeyMapping);



        //Generate window controller for this level
        if(currentLevelWindowController == null) { //If no current controller is defined - i.e we're yet to enter the first level
            currentLevelWindowController = new LevelWindowController(levelObjects, 
                                                                    levelLoader.getPrefferedObjectSize(levelNumber), 
                                                                    "SokobanGLOO - Niveau "+Integer.toString(levelNumber));

            //Also attach the keyboard handler
            currentLevelWindowController.getWindow().addKeyListener(currentKeyboardHandler);
        }else{
            currentLevelWindowController = new LevelWindowController(levelObjects, 
                                                                    currentLevelWindowController.getWindow(),
                                                                    levelLoader.getPrefferedObjectSize(levelNumber), 
                                                                    "SokobanGLOO - Niveau "+Integer.toString(levelNumber));
        }

    }

    public static void main(String[] args) {
        try {
            goToLevel(-1);
        } catch (InvalidLevelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        SwingUtilities.invokeLater(currentLevelWindowController);
        
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
