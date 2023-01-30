package Modele;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("9cf4a86d-907b-4e94-a9bb-62116b74b49b")
public class Box extends MovableObject implements Collisionable, Drawable {
    public Box(int col, int row) {
        super(col, row);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Box clone(){
        return new Box(this.column, this.row);
    }

    @objid ("0803debf-b931-4dcb-84e1-7749206e4b48")
    private boolean isOnTarget;

    @objid ("c18683db-4d7c-4b86-b5cb-2da8df2028b8")
    public void draw() {
    }

    @objid ("48baeedc-693d-4cf8-8d58-acf029b75404")
    public boolean getIsOnTarget() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.isOnTarget;
    }

    @objid ("650f9d71-b855-4cfe-9ab6-10c165f3a673")
    public void setIsOnTarget(boolean value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.isOnTarget = value;
    }

}
