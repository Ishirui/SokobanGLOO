package Controle;

import java.util.List;
import Modele.BaseObject;
import Modele.Box;
import Modele.Grid;
import Modele.PhysicalObject;
import Modele.Target;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("01ebe963-9455-46bf-86b7-7006c049fa5e")
public class Controleur {
    Grid grid;
    Gardien gardien;
    
    @objid ("d5fbc405-386a-4326-b344-6d4d8533ff17")
    public void drawAll(List<BaseObject> toDraw) {
    }

    @objid ("bc6c40f3-01b3-4f09-a965-d412ca1d8894")
    public void move(Direction direction) {
        PhysicalObject[] to_move = gardien.checkMove(grid, direction);
        if(to_move.length == 0) return; //An empty list represents an impossible move


        try{
            grid.moveObjects(direction, to_move);
        } catch(Exception exception) {
            System.out.println("Error while moving !"+exception);
        }

    }

    @objid ("9dda8c78-746c-48cc-b921-0a062a7f69fe")
    public boolean checkWin() {
        for(Box box:grid.getBoxes()){
            int col = box.getColumn();
            int row = box.getRow();
            
            boolean onTarget = false;

            for(Target target:grid.getTargets()){
                if(col == target.getColumn() && row == target.getRow()){
                    onTarget = true;
                    break;
                }
            }

            if(!onTarget) return false;

        }
        return true;
    }

}
