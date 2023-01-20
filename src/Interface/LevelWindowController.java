package Interface;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

import Modele.Drawable;

public class LevelWindowController implements Runnable{

    private Drawable[] levelElements;
    private int objectSize;

    private String windowTitle;
    private Dimension windowSize;

    private JFrame window;

    LevelWindowController(Drawable[] levelElements, JFrame existingWindow, int objectSize, String windowTitle){
        //Use this constructor when reusing a previously spawned window
        this.levelElements = levelElements;
        this.objectSize = objectSize;
        this.windowTitle = windowTitle;

        //Infer window dimensions from the highest col and row values in the levelElements
        int maxCol = 0, maxRow = 0;

        for(Drawable obj:levelElements){
            int col = obj.getColumn();
            int row = obj.getRow();

            if(col > maxCol) maxCol = col;
            if(row > maxRow) maxRow = row;
        }

        this.windowSize = new Dimension(objectSize*(maxCol+1), objectSize*(maxRow+1));

        existingWindow.setPreferredSize(this.windowSize);
        existingWindow.setTitle(this.windowTitle);

        this.window = existingWindow;

    }
    
    LevelWindowController(Drawable[] levelElements, int objectSize, String windowTitle){
        //Use this constructor for creating a new window
        this(levelElements, new JFrame(windowTitle), objectSize, windowTitle);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    public void run() {

        DrawHandler drawHandler = null;
        try {
            drawHandler = new DrawHandler(levelElements, objectSize);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        window.add(drawHandler);
        window.pack();
        window.setVisible(true);
    }
}
