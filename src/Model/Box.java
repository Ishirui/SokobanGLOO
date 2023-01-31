package Model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

public class Box extends MovableObject implements Collisionable, Drawable {
    public Box(int col, int row) {
        super(col, row);
        //TODO Auto-generated constructor stub
    }

    private boolean isOnTarget;

    public void draw() {
    }

    public boolean getIsOnTarget() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.isOnTarget;
    }

    public void setIsOnTarget(boolean value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.isOnTarget = value;
    }

}
