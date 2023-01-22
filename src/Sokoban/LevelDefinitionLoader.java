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
        BaseObject[] testElements = { new Wall(0,0), new Wall(1, 0), new Wall(2,0), new Wall(3, 0),
            new Wall(0,1), new Box(1, 1), new Floor(2, 1), new Wall(3, 1),
            new Wall(0,2), new Floor(1, 2), new Player(2, 2), new Wall(3, 2),
            new Wall(0,3), new Wall(1, 3), new Wall(2,3), new Wall(3, 3),
            new Target(2,1)};

        //BaseObject[] testElements = { new Player(0,0), new Floor(0,1)};


        if(levelNumber == -1) return testElements;
        return new BaseObject[] {};
    }

    public int getPrefferedObjectSize(int levelNumber){
       //TODO: Implement method
       return 64;
    }
}
