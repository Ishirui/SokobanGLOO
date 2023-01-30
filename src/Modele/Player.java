package Modele;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("3cd497a3-09a1-4c56-832d-7a85c6799566")
public class Player extends PhysicalObject implements Collisionable, Drawable {
    public Player(int col, int row) {
        super(col, row);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Player clone(){
        return new Player(this.column, this.row);
    }

}
