package Sokoban;

import Controller.Controller;
import Sokoban.SokobanExceptions.InvalidLevelException;

public class Sokoban {
    static Controller controller = new Controller();
    public static void main(String[] args){
        try {
            controller.goToLevel(0);
        } catch (InvalidLevelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        controller.launch();
    }
}
