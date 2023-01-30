package Interface;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Modele.BaseObject;
import Modele.Drawable;
import Modele.PhysicalObject;
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
        
        ArrayList<Drawable> levelElements = new ArrayList<Drawable>();
        ArrayList<Drawable> overlayElements = new ArrayList<Drawable>();

        for(BaseObject item:items){
            if(!(item instanceof Drawable)) throw new InvalidLevelException("A non-drawable item was passed to the window controller !");
            
            if(item instanceof PhysicalObject){
                levelElements.add((Drawable) item);
            }else{
                overlayElements.add((Drawable) item);
            } 
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
            this.panel = new LevelPanel(levelElements.toArray(new Drawable[]{}), overlayElements.toArray(new Drawable[]{}), objectSize);
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
    
    public void setKeyboardHandler(KeyboardHandler keyboardHandler){
        for(KeyListener listener:window.getKeyListeners()) window.removeKeyListener(listener); //Remove all listeners on the window
        window.addKeyListener(keyboardHandler);
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
