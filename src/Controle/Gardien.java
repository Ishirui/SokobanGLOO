package Controle;

import Modele.Collisionable;

import java.util.Map;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import Modele.Grid;
import Modele.MovableObject;
import Modele.PhysicalObject;
import Modele.Player;

@objid ("675dfa58-6bee-4218-8b7b-bfbf681fe9f1")
public class Gardien {
    @objid ("13c0dc74-ae2d-4d8f-bc14-900825df4f1a")
    public PhysicalObject[] checkMove(Grid grid, Direction direction) {
        //Returns the list of objects to move, in order (see implementation of Modele.Grid.moveObjects). If the move is not possible, return an empty array
        
        Player player = grid.getPlayer();
        
        int old_row = player.getRow();
        int old_col = player.getColumn();

        int[] delta = direction.getDelta();
        int new_row = old_row + delta[0];
        int new_col = old_col + delta[1];

        PhysicalObject to_go = grid.getGridMatrix()[new_row][new_col];

        if(!(to_go instanceof Collisionable)) return new PhysicalObject[]{player}; //If the destination is not collisionable (e.g Floor), we can return immediately
        if(!(to_go instanceof MovableObject)) return new PhysicalObject[]{}; //If the destination is collisionable AND not movable (e.g Wall), we can also return immediately

        //Handle the case where the player has to push a MovableObject (e.g Box)
        int new_row_2 = old_row + delta[0];
        int new_col_2 = old_col + delta[1];

        PhysicalObject to_go_2 = grid.getGridMatrix()[new_row_2][new_col_2];
        if (to_go_2 instanceof Collisionable) return new PhysicalObject[]{}; //If the Box has to move into something collisionable - including another box ! - it can't, so the move isn't possible

        return new PhysicalObject[]{to_go, player};

    }
}
