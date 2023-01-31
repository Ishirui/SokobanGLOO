package View;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

public enum InputToken{
    //These four are useful both in menus and in a level
    UP(-1,-0),
    RIGHT(0,1),
    DOWN(1,0),
    LEFT(0,-1),

    //Dummy value - could have used null instead
    NOTHING(0,0),

    //This is only used for for control
    RESET(0,0);

    private int deltaRow;
    private int deltaCol;

    private InputToken(int deltaRow, int deltaCol){
        this.deltaRow = deltaRow;
        this.deltaCol = deltaCol;
    }

    public int[] getDelta(){
        return new int[]{deltaRow,deltaCol};
    }

    public InputToken getOpposite(){
        switch(this){
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                return this;
        }
    }
}


