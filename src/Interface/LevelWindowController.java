package Interface;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

import Modele.BaseObject;
import Modele.Drawable;
import Sokoban.Sokoban.InvalidLevelException;

public class LevelWindowController implements WindowController{

    private Drawable[] levelElements;
    private int objectSize;

    private String windowTitle;
    private Dimension windowSize;

    private JFrame window;
    private DrawHandler drawHandler;

    public LevelWindowController(BaseObject[] items, JFrame existingWindow, int objectSize, String windowTitle) throws InvalidLevelException{
        //Use this constructor when reusing a previously spawned window
        
        //Filter out all non-drawable objects (there shouldn't be any, but Java won't allow the typecast)
        this.levelElements = new Drawable[items.length];
        for(int i = 0; i < items.length; i++){
            BaseObject item = items[i];
            if(!(item instanceof Drawable)) throw new InvalidLevelException("Invalid level ! A non-drawable object was passed to the window controller");

            levelElements[i] = (Drawable) item;
        }
        
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
        setupWindow();

    }
    
    public LevelWindowController(BaseObject[] items, int objectSize, String windowTitle) throws InvalidLevelException{
        //Use this constructor for creating a new window
        this(items, new JFrame(windowTitle), objectSize, windowTitle);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void setupWindow(){
        try {
            drawHandler = new DrawHandler(levelElements, objectSize);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        window.add(drawHandler);
        window.pack();
    }
    
    
    public JFrame getWindow(){
        return this.window;
    }

    public void refresh() {
        window.setVisible(true);
        drawHandler.repaint();
    }
}
