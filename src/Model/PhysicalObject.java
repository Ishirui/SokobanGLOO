package Model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import View.InputToken;

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
