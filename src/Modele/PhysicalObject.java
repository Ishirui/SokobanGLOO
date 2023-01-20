package Modele;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import Controle.InputToken;

@objid ("4b4efabb-7c95-4a11-a263-accb7b3b73da")
public abstract class PhysicalObject extends BaseObject {
    public PhysicalObject(int col, int row) {
        super(col, row);
        //TODO Auto-generated constructor stub
    }

    public void move(InputToken direction) {
        int[] delta = direction.getDelta();
        
        this.row = this.row + delta[0];
        this.column = this.column + delta[1];
    }
}
