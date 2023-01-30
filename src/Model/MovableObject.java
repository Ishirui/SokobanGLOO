package Model;

import Controle.InputToken;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("c5edcf6c-4b52-4985-9bac-c6a5b1325d97")
public abstract class MovableObject extends PhysicalObject {
    public MovableObject(int col, int row) {
        super(col, row);
        //TODO Auto-generated constructor stub
    }

    @objid ("2aff23e6-32d6-42dc-8800-9fdcc65a830f")
    @Override
    public void move(InputToken direction) {
        super.move(direction); //Temporary implementation, maybe don't even need the override
    }

}
