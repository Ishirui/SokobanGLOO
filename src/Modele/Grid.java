package Modele;

import java.util.ArrayList;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import Sokoban.Sokoban.InvalidLevelException;


@objid ("31534487-cfc0-4100-b387-4c08228e3a47")
public class Grid {
    @objid ("157d9201-2d75-4a75-9896-3d677bace388")
    private Box[] boxes;

    @objid ("d523b5cd-fb5f-416f-932e-a383b28e883e")
    private int height;

    @objid ("8372475c-4fad-4f79-b2b1-07866e262630")
    private PhysicalObject[][] gridMatrix;

    @objid ("8c390077-fe17-4c0f-befb-9188c027168b")
    private Player player;

    @objid ("d693ad7b-3f70-4190-88b1-1b1bbd02bd5c")
    private Target[] targets;

    @objid ("42b4f959-a876-4ab4-849a-3bf6036905fb")
    private int width;

    public Grid(BaseObject[] items) throws InvalidLevelException{
        //Infer grid dimensions from item coordinates
        int maxCol = 0, maxRow = 0;

        for(BaseObject obj:items){
            int col = obj.getColumn();
            int row = obj.getRow();

            if(col > maxCol) maxCol = col;
            if(row > maxRow) maxRow = row;
        }

        this.width = maxCol + 1;
        this.height = maxRow + 1;

        this.gridMatrix = new PhysicalObject[width][height];

        ArrayList<Box> boxesTemp = new ArrayList<Box>();
        ArrayList<Target> targetsTemp = new ArrayList<Target>();

        for(BaseObject obj:items){
            int row = obj.getRow();
            int col = obj.getColumn();

            if (obj instanceof PhysicalObject){
                if(gridMatrix[col][row] != null) throw new InvalidLevelException("Invalid level data ! Two objects occupy the same spot.");
                gridMatrix[col][row] = (PhysicalObject) obj;
            } 

            if (obj instanceof Player){
                if(player != null) throw new InvalidLevelException("Invalid level data ! Two players.");
                player = (Player) obj;
            }

            if (obj instanceof Box) boxesTemp.add((Box) obj);
            if (obj instanceof Target) targetsTemp.add((Target) obj);
        }

        this.boxes = boxesTemp.toArray(new Box[0]); //Little quirk of the toArray function: the argument is used to determine the return type of the method
        this.targets = targetsTemp.toArray(new Target[0]);
    }

    @objid ("64f70b5f-3561-457c-8868-0c0a0553af19")
    public Player getPlayer() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.player;
    }

    @objid ("4f1136e8-51b1-4725-9619-bd6baedb8e4f")
    public PhysicalObject[][] getGridMatrix() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.gridMatrix;
    }

    @objid ("d8cb184e-d535-4f47-bff2-801d01535072")
    public Target[] getTargets() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.targets;
    }

    @objid ("14331b11-ce31-474d-bfd7-f7f22d7aa3ae")
    public Box[] getBoxes() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.boxes;
    }

    @objid ("323b4b29-b418-4e0a-87b3-83ee48e35c0b")
    public int getHeight() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.height;
    }

    @objid ("e89ac4ec-5308-4c64-9d02-31c930b8e97a")
    public int getWidth() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.width;
    }

}

