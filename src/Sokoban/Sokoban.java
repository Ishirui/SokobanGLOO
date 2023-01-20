package Sokoban;

import javax.swing.SwingUtilities;

import Controle.GameController;
import Interface.LevelWindowController;
import Modele.BaseObject;
import Modele.Drawable;

public class Sokoban {

    private static int currentLevelNumber;

    private static GameController currentLevelController;
    private static LevelWindowController currentLevelWindowController;

    private static LevelDefinitionLoader levelLoader = new LevelDefinitionLoader();

    static void goToLevel(int levelNumber) throws InvalidLevelException{
        Sokoban.currentLevelNumber = levelNumber;
        BaseObject[] levelObjects = levelLoader.getLevelObjects(levelNumber);


        //Generate window controller for this level
        if(currentLevelWindowController == null) { //If no current controller is defined - i.e we're yet to enter the first level
            currentLevelWindowController = new LevelWindowController(levelObjects, 
                                                                    levelLoader.getPrefferedObjectSize(levelNumber), 
                                                                    "SokobanGLOO - Niveau "+Integer.toString(levelNumber));
        }else{
            currentLevelWindowController = new LevelWindowController(levelObjects, 
                                                                    currentLevelWindowController.getWindow(),
                                                                    levelLoader.getPrefferedObjectSize(levelNumber), 
                                                                    "SokobanGLOO - Niveau "+Integer.toString(levelNumber));
        }

        //Generate game controller for this level
        currentLevelController = new GameController(levelObjects);

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

}
