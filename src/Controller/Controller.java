package Controller;

import Model.Grid;
import Model.PhysicalObject;
import Sokoban.SokobanExceptions.SokobanRuntimeException;
import View.LevelView;

public class Controller{

    private Guardian guardian = new Guardian();

    private int currentLevelNumber;
    private Grid currentGrid;

    private LevelView currentView;

    private LevelLoader levelLoader = new LevelLoader("/home/ishirui/Documents/Code/SokobanGLOO/levels");

    public void doMove(InputToken token) throws SokobanRuntimeException {
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
            
            if(!old_obj.equals(obj)){
                throw new SokobanRuntimeException("The object to move wasn't at the right place in the matrix");
            }
            
            PhysicalObject new_obj = currentGrid.getGridMatrix()[newRow][newCol];

            //Swap the two objects, representing the move in the gridMatrix
            currentGrid.getGridMatrix()[row][col] = new_obj;
            currentGrid.getGridMatrix()[newRow][newCol] = old_obj;
            

            //Update the object coordinates
            obj.move(token);
            new_obj.move(token.getOpposite());
        }
    }

    public void goToLevel(int levelNumber){
        levelLoader.loadLevel(currentLevelNumber);
        currentGrid = new Grid(levelLoader.getLevelObjects());
        currentView = new LevelView(levelLoader.getLevelObjects());
    }

    public void resetLevel() {
        goToLevel(currentLevelNumber);
    }

}