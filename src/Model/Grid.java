package Model;

import java.util.ArrayList;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import Sokoban.SokobanExceptions.InvalidLevelException;


public class Grid {
    private PhysicalObject[][] gridMatrix;
    private Player player;
    private Target[] targets;
    private Box[] boxes;

    private int height;
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

        this.gridMatrix = new PhysicalObject[height][width];

        ArrayList<Box> boxesTemp = new ArrayList<Box>();
        ArrayList<Target> targetsTemp = new ArrayList<Target>();

        for(BaseObject obj:items){
            int row = obj.getRow();
            int col = obj.getColumn();

            if (obj instanceof PhysicalObject){
                if(gridMatrix[row][col] != null) throw new InvalidLevelException("Invalid level data ! Two objects occupy the same spot.");
                gridMatrix[row][col] = (PhysicalObject) obj;
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

    public Player getPlayer() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.player;
    }

    public PhysicalObject[][] getGridMatrix() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.gridMatrix;
    }

    public Target[] getTargets() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.targets;
    }

    public Box[] getBoxes() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.boxes;
    }
    public int getHeight() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.height;
    }

    public int getWidth() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.width;
    }

}

