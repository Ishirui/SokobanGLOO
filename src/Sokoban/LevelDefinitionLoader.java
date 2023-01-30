package Sokoban;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import Modele.BaseObject;
import Modele.Floor;
import Modele.Player;
import Modele.Target;
import Modele.Wall;
import Modele.Box;

public class LevelDefinitionLoader {
    private File[] levelFiles;

    private int currentLevelNumber;
    private BaseObject[] currentLevelObjects;
    private int currentPrefferedSize;

    public LevelDefinitionLoader(String levelsLocation){
        File folder = new File(levelsLocation);
        ArrayList<File> levelFiles = new ArrayList<File>();
        for(File file:folder.listFiles()){
            String name = file.getName();
            if(!name.endsWith(".txt")) continue;
            if(!name.startsWith("level")) continue;

            int levelNumber = Integer.parseInt(name.substring(5, name.indexOf(".")));
            levelFiles.add(levelNumber, file);
        }
        this.levelFiles = levelFiles.toArray(new File[]{});
    }

    public void loadLevel(int levelNumber) throws IOException{
        File levelFile = levelFiles[levelNumber];
        
        //FORMAT
        // # = Wall
        // @ = Player
        // $ = Box

        //   = Floor (Space)

        // . = Target
        // * = Box on Target
        // + = Player on Target

        //The FIRST line should contain the preffered object size - defaults to 32

        ArrayList<BaseObject> objects = new ArrayList<BaseObject>();
        BufferedReader br = new BufferedReader(new FileReader(levelFile));
        
        int i = 0;
        int j = 0;
        String currentLine = br.readLine();
        
        //Try interpreting the first line as an integer. If it fails, it's probably supposed to be the first line of the level - so default to 32.
        try{
            currentPrefferedSize = Integer.parseInt(currentLine);
            currentLine = br.readLine(); //Only if the conversion was successful, go to the next line
        }catch(NumberFormatException e){
            System.out.println("Preffered object size not specified, defaulting to 32px");
            currentPrefferedSize = 32;
        }


        while(currentLine != null){
            j = 0;
            for(char character:currentLine.toCharArray()){
                switch(character){
                    case '#':
                        objects.add(new Wall(i,j));
                        break;
                    case '@':
                        objects.add(new Player(i,j));
                        break;
                    case '$':
                        objects.add(new Box(i,j));
                        break;
                    case ' ':
                        objects.add(new Floor(i,j));
                        break;
                    case '.':
                        objects.add(new Floor(i,j));
                        objects.add(new Target(i,j));
                        break;
                    case '*':
                        objects.add(new Box(i,j));
                        objects.add(new Target(i,j));
                        break;
                    case '+':
                        objects.add(new Player(i,j));
                        objects.add(new Target(i,j));
                        break;
                }
                j++;
            }
            i++;
            currentLine = br.readLine();
        }

        br.close();

        currentLevelObjects = objects.toArray(new BaseObject[]{}); //Again, the argument is used here to set the type of the resulting array
        currentLevelNumber = levelNumber;
    }

    public BaseObject[] getLevelObjects(){
        //Testing level TODO:Remove
        // BaseObject[] testElements = { 
        //     new Wall(0,0), new Wall(1, 0), new Wall(2,0), new Wall(3, 0), new Wall(4, 0),
        //     new Wall(0,1), new Floor(1, 1), new Floor(2, 1), new Floor(3, 1), new Wall(4, 1),
        //     new Wall(0,2), new Floor(1, 2), new Box(2, 2), new Player(3, 2), new Wall(4, 2),
        //     new Wall(0,3), new Floor(1, 3), new Floor(2, 3), new Floor(3, 3), new Wall(4, 3),
        //     new Wall(0,4), new Wall(1, 4), new Wall(2,4), new Wall(3, 4), new Wall(4, 4),
        //     new Target(3,3)};
        return currentLevelObjects;
    }

    public int getPrefferedObjectSize(){
       return currentPrefferedSize;
    }
}
