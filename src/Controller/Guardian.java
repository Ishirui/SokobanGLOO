package Controller;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import Model.Collisionable;
import Model.Grid;
import Model.MovableObject;
import Model.PhysicalObject;
import Model.Player;
import View.InputToken;

public class Guardian {
    public PhysicalObject[] checkMove(Grid grid, InputToken direction) {
        //Returns the list of objects to move, in order (see implementation of Controleur.move). If the move is not possible, return an empty array
        
        Player player = grid.getPlayer();
        
        int old_row = player.getRow();
        int old_col = player.getColumn();

        int[] delta = direction.getDelta();
        int new_row = old_row + delta[0];
        int new_col = old_col + delta[1];

        PhysicalObject to_go;

        try{
            to_go = grid.getGridMatrix()[new_row][new_col];
        } catch(ArrayIndexOutOfBoundsException e){
            //An OOB Exception means that we tried to access an object outside of the game area -- we'll implicitly assume it's a wall
            return new PhysicalObject[]{};
        }

        if(!(to_go instanceof Collisionable)) return new PhysicalObject[]{player}; //If the destination is not collisionable (e.g Floor), we can return immediately
        if(!(to_go instanceof MovableObject)) return new PhysicalObject[]{}; //If the destination is collisionable AND not movable (e.g Wall), we can also return immediately

        //Handle the case where the player has to push a MovableObject (e.g Box)
        int new_row_2 = new_row + delta[0];
        int new_col_2 = new_col + delta[1];

        PhysicalObject to_go_2 = grid.getGridMatrix()[new_row_2][new_col_2];
        if (to_go_2 instanceof Collisionable) return new PhysicalObject[]{}; //If the Box has to move into something collisionable - including another box ! - it can't, so the move isn't possible

        return new PhysicalObject[]{to_go, player}; //Always put the box to move FIRST (see implementation of Modele.Grid.moveObjects)

    }
}
