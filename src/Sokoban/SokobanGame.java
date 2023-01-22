package Sokoban;

import javax.swing.SwingUtilities;

import Controle.LevelLogicController;
import Controle.LogicController;
import Interface.KeyboardHandler;
import Interface.LevelWindowController;
import Interface.WindowController;
import Modele.BaseObject;
import Sokoban.Sokoban.InvalidLevelException;

public class SokobanGame {
    private LogicController currentLogicController;
    private WindowController currentWindowController;
    private KeyboardHandler currentKeyboardHandler;
    
    private int currentLevelNumber;

    private LevelDefinitionLoader levelLoader = new LevelDefinitionLoader();

    void goToLevel(int levelNumber) throws InvalidLevelException{
        currentLevelNumber = levelNumber;
        BaseObject[] levelObjects = levelLoader.getLevelObjects(levelNumber);

        //Generate game controller for this level
        currentLogicController = new LevelLogicController(levelObjects);

        //Generate keyboard controller with the appropriate mapping, and attach the game controller to it
        currentKeyboardHandler = new KeyboardHandler(currentLogicController, Sokoban.levelKeyMapping);

        //Generate window controller for this level
        if(currentWindowController == null) { //If no current controller is defined - i.e we're yet to enter the first level
            currentWindowController = new LevelWindowController(levelObjects, 
                                                                    levelLoader.getPrefferedObjectSize(levelNumber), 
                                                                    "SokobanGLOO - Niveau "+Integer.toString(levelNumber));

        }else{
            currentWindowController = new LevelWindowController(levelObjects, 
                                                                    currentWindowController.getWindow(),
                                                                    levelLoader.getPrefferedObjectSize(levelNumber), 
                                                                    "SokobanGLOO - Niveau "+Integer.toString(levelNumber));
        }

        currentWindowController.setKeyboardHandler(currentKeyboardHandler);            
    }

    void launch(){
        SwingUtilities.invokeLater(currentWindowController);
    }
}
