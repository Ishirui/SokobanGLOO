package Controle;

import java.util.List;
import Modele.BaseObject;
import Modele.Box;
import Modele.Grid;
import Modele.PhysicalObject;
import Modele.Target;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("01ebe963-9455-46bf-86b7-7006c049fa5e")
public class GameController {
    Grid grid;
    Gardien gardien;
    
    @objid ("d5fbc405-386a-4326-b344-6d4d8533ff17")
    public void drawAll(List<BaseObject> toDraw) {
    }

    @objid ("bc6c40f3-01b3-4f09-a965-d412ca1d8894")
    public void move(Direction direction) throws Exception {
        //NOTE: This method is sensitive to the ordering of the to_move list.
        //Especially when moving a box and a player, you should always have the box first.
        //Usually, to_move should be {player} or {box, player}


        PhysicalObject[] to_move = gardien.checkMove(grid, direction);
        if(to_move.length == 0) return; //An empty list represents an impossible move

    
        //Convert enum to row and column changes
        int[] delta = direction.getDelta();
        int deltaRow = delta[0]; 
        int deltaCol = delta[1]; 
        
        for(PhysicalObject obj:to_move){
            //Current coordinates in the matrix of the object to move
            int row = obj.getRow();
            int col = obj.getColumn();

            int newRow = row + deltaRow;
            int newCol = col + deltaCol;

            PhysicalObject old_obj = grid.getGridMatrix()[row][col];
            
            if(!old_obj.equals(obj)){
                throw new Exception("The object to move wasn't at the right place in the matrix");
            }
            
            PhysicalObject new_obj = grid.getGridMatrix()[newRow][newCol];

            //Swap the two objects, representing the move in the gridMatrix
            grid.getGridMatrix()[row][col] = new_obj;
            grid.getGridMatrix()[newRow][newCol] = old_obj;
            

            //Update the object coordinates
            obj.move(direction);
            new_obj.move(direction.getOpposite());
        }
            

    }

    public void checkOnTarget(){
        //Checks which boxes are on targets
        //Should be run after each move
        Box[] boxes = grid.getBoxes();
        Target[] targets = grid.getTargets();   
        //Note that the targets list is NOT a matrix, only a list
        //Thus, better to iterate through targets and access boxes through coordinates, rather than the opposite: this saves iterating through an array

        //A priori, all boxes are NOT on targets
        for(Box box:boxes) box.setIsOnTarget(false);


        for(Target target: targets){
            int col = target.getColumn();
            int row = target.getRow();

            PhysicalObject obj = grid.getGridMatrix()[row][col];

            if(obj instanceof Box) ((Box) obj).setIsOnTarget(true);
        }
    }

    @objid ("9dda8c78-746c-48cc-b921-0a062a7f69fe")
    public boolean checkWin() {
        for(Box box:grid.getBoxes()) if(!box.getIsOnTarget()) return false;
        return true;
    }

}
