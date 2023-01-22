package Sokoban;


import Modele.BaseObject;
import Modele.Floor;
import Modele.Player;
import Modele.Target;
import Modele.Wall;
import Modele.Box;

public class LevelDefinitionLoader {
    

    public BaseObject[] getLevelObjects(int levelNumber){
        //TODO: Implement method

        //Testing level TODO:Remove
        BaseObject[] testElements = { 
            new Wall(0,0), new Wall(1, 0), new Wall(2,0), new Wall(3, 0), new Wall(4, 0),
            new Wall(0,1), new Floor(1, 1), new Floor(2, 1), new Floor(3, 1), new Wall(4, 1),
            new Wall(0,2), new Floor(1, 2), new Box(2, 2), new Player(3, 2), new Wall(4, 2),
            new Wall(0,3), new Floor(1, 3), new Floor(2, 3), new Floor(3, 3), new Wall(4, 3),
            new Wall(0,4), new Wall(1, 4), new Wall(2,4), new Wall(3, 4), new Wall(4, 4),
            new Target(3,3)};

        //BaseObject[] testElements = { new Player(0,0), new Floor(0,1)};


        if(levelNumber == -1) return testElements;
        return new BaseObject[] {};
    }

    public int getPrefferedObjectSize(int levelNumber){
       //TODO: Implement method
       return 64;
    }
}
