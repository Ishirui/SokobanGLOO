package Interface;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

import Modele.Drawable;

public class LevelWindowController implements Runnable{

    private Drawable[] levelElements;
    private int objectSize;

    private String windowLabel;
    private Dimension windowSize;
    
    LevelWindowController(Drawable[] levelElements, int objectSize, String windowLabel){
        this.levelElements = levelElements;
        this.objectSize = objectSize;
        this.windowLabel = windowLabel;

        //Infer window dimensions from the highest col and row values in the levelElements
        int maxCol = 0, maxRow = 0;

        for(Drawable obj:levelElements){
            int col = obj.getColumn();
            int row = obj.getRow();

            if(col > maxCol) maxCol = col;
            if(row > maxRow) maxRow = row;
        }

        this.windowSize = new Dimension(objectSize*(maxCol+1), objectSize*(maxRow+1));
    }
    
    
    public void run() {
        JFrame fenetre = new JFrame(windowLabel);
        fenetre.setPreferredSize(windowSize);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        DrawHandler drawHandler = null;
        try {
            drawHandler = new DrawHandler(levelElements, objectSize);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        fenetre.add(drawHandler);
        fenetre.pack();
        fenetre.setVisible(true);
    }
}
