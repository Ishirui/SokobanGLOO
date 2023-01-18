package Controle;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("6564f8da-09a3-422d-9e44-73c4632b9b50")
public enum Direction {
    UP(-1,-0),
    RIGHT(0,1),
    DOWN(1,0),
    LEFT(0,-1);

    private int deltaRow;
    private int deltaCol;

    private Direction(int deltaRow, int deltaCol){
        this.deltaRow = deltaRow;
        this.deltaCol = deltaCol;
    }

    public int[] getDelta(){
        return new int[]{deltaRow,deltaCol};
    }

    public Direction getOpposite(){
        return switch(this){
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}


