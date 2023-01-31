package Model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

public abstract class BaseObject implements Cloneable{
    public BaseObject(int col, int row){
        this.column = col;
        this.row = row;
    }
    
    
    protected int column;

    protected int row;

    public int getColumn() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.column;
    }

    public int getRow() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.row;
    }

}
