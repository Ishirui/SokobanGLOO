package Interface;

import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Modele.BaseObject;
import Modele.Drawable;
import Sokoban.Sokoban.InvalidLevelException;

public class LevelWindowController implements WindowController{

    private Drawable[] levelElements;
    private int objectSize;

    private String windowTitle;
    private Dimension windowSize;

    private JFrame window;
    private JPanel panel;

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
        this.window = existingWindow;
        try {
            this.panel = new LevelPanel(levelElements, objectSize);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Load images
        setupWindow();
    }
    
    public LevelWindowController(BaseObject[] items, int objectSize, String windowTitle) throws InvalidLevelException{
        //Use this constructor for creating a new window
        this(items, new JFrame(windowTitle), objectSize, windowTitle);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void setupWindow(){
        window.setPreferredSize(windowSize);
        window.setTitle(windowTitle);
        window.add(panel);
        window.pack();
        window.setVisible(true);
    }
    
    
    public JFrame getWindow(){
        return this.window;
    }

    public void refresh() {
        panel.repaint();
        window.repaint();
        window.requestFocus();
    }
}
