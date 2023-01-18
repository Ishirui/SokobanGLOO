package Modele;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("80c1aec3-91bc-4423-8c1b-e9f2d60c5405")
public class Wall extends PhysicalObject implements Collisionable, Drawable {
    public Wall(int col, int row) {
        super(col, row);
        //TODO Auto-generated constructor stub
    }

}
