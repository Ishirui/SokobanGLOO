package Controller;

import java.io.IOException;

import javax.swing.SwingUtilities;

import Model.Box;
import Model.Grid;
import Model.PhysicalObject;
import Model.Target;
import Sokoban.SokobanExceptions.InvalidLevelException;
import Sokoban.SokobanExceptions.SokobanRuntimeException;
import View.InputToken;
import View.KeyboardHandler;
import View.LevelView;

public class Controller{

    private Guardian guardian = new Guardian();

    private int currentLevelNumber;
    private Grid currentGrid;

    private LevelView currentView;

    private LevelLoader levelLoader = new LevelLoader("levels");

    public void doMove(InputToken token) throws SokobanRuntimeException, InvalidLevelException {
        //NOTE: This method is sensitive to the ordering of the to_move list.
        //Especially when moving a box and a player, you should always have the box first.
        //Usually, to_move should be {player} or {box, player}

        PhysicalObject[] to_move = guardian.checkMove(currentGrid, token);
        if(to_move.length == 0) return; //An empty list represents an impossible move

        //Convert enum to row and column changes
        int[] delta = token.getDelta();
        int deltaRow = delta[0]; 
        int deltaCol = delta[1]; 
        
        for(PhysicalObject obj:to_move){
            //Current coordinates in the matrix of the object to move
            int row = obj.getRow();
            int col = obj.getColumn();

            int newRow = row + deltaRow;
            int newCol = col + deltaCol;

            PhysicalObject old_obj = currentGrid.getGridMatrix()[row][col];
            
            PhysicalObject new_obj = currentGrid.getGridMatrix()[newRow][newCol];

            //Swap the two objects, representing the move in the gridMatrix
            currentGrid.getGridMatrix()[row][col] = new_obj;
            currentGrid.getGridMatrix()[newRow][newCol] = old_obj;
            

            //Update the object coordinates
            obj.move(token);
            new_obj.move(token.getOpposite());
            
            checkOnTarget();
            if(checkWin()) goToLevel(currentLevelNumber+1);

            currentView.run();
        }
    }

    public void goToLevel(int levelNumber) throws InvalidLevelException{
        currentLevelNumber = levelNumber;

        try {
            levelLoader.loadLevel(currentLevelNumber);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        currentGrid = new Grid(levelLoader.getLevelObjects());

        if(currentView == null){
            //If the window has not been initialized
            currentView = new LevelView(levelNumber, levelLoader.getLevelObjects(), levelLoader.getPrefferedObjectSize());
        }else{
            //Otherwise, reuse it
            currentView = new LevelView(currentView.getFrame(), levelNumber,levelLoader.getLevelObjects(), levelLoader.getPrefferedObjectSize());
        }

        currentView.setKeyboardHandler(new KeyboardHandler(this));
        currentView.run();
    }

    public void resetLevel() throws InvalidLevelException {
        goToLevel(currentLevelNumber);
    }

    public void checkOnTarget(){
        //Checks which boxes are on targets
        //Should be run after each move
        Box[] boxes = currentGrid.getBoxes();
        Target[] targets = currentGrid.getTargets();   
        //Note that the targets list is NOT a matrix, only a list
        //Thus, better to iterate through targets and access boxes through coordinates, rather than the opposite: this saves iterating through an array

        //A priori, all boxes are NOT on targets
        for(Box box:boxes) box.setIsOnTarget(false);


        for(Target target: targets){
            int col = target.getColumn();
            int row = target.getRow();

            PhysicalObject obj = currentGrid.getGridMatrix()[row][col];

            if(obj instanceof Box) ((Box) obj).setIsOnTarget(true);
        }
    }

    public boolean checkWin() {
        for(Box box:currentGrid.getBoxes()) if(!box.getIsOnTarget()) return false;
        return true;
    }


    public void launch(){
        SwingUtilities.invokeLater(currentView);
    }

}