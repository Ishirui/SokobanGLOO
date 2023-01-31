package View;

import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import Model.BaseObject;
import Model.Drawable;
import Model.Target;

public class LevelView implements Runnable{
    private JFrame frame;

    public LevelView(int levelNumber, BaseObject[] levelItems, int objectSize){
        //When no existing window is available
        this(new JFrame(), levelNumber, levelItems, objectSize);
    }
    
    public LevelView(JFrame frame, int levelNumber, BaseObject[] levelItems, int objectSize){
        ArrayList<Drawable> drawableItems = new ArrayList<Drawable>();
        ArrayList<Drawable> overlayItems = new ArrayList<Drawable>();
        
        //Infer windowSize from objectSize and objects
        int maxCol = 0, maxRow = 0;
        
        //Also sort between overlays and non-overlays
        for(BaseObject obj:levelItems){
            int col = obj.getColumn();
            int row = obj.getRow();

            if(col > maxCol) maxCol = col;
            if(row > maxRow) maxRow = row;

            if(obj instanceof Target) overlayItems.add((Drawable) obj);
            else drawableItems.add((Drawable) obj);
        }
        Dimension windowSize = new Dimension(objectSize*(maxCol+1), objectSize*(maxRow+3));
        
        frame.setVisible(false);
        frame.getContentPane().removeAll();
        frame.repaint();

        frame.setSize(windowSize);
        frame.setResizable(false);
        frame.setTitle("Sokoban - Niveau "+String.valueOf(levelNumber));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new LevelPanel(drawableItems.toArray(new Drawable[]{}), overlayItems.toArray(new Drawable[]{}), objectSize));
        //frame.pack();

        frame.revalidate();

        frame.setVisible(true);

        this.frame = frame;
    }

    public JFrame getFrame(){
        return frame;
    }

    @Override
    public void run(){
        //The run method will be used by swing and needs to refresh the view
        frame.repaint();
    }

    public void setKeyboardHandler(KeyboardHandler keyboardHandler){
        for(KeyListener listener:frame.getKeyListeners()) frame.removeKeyListener(listener); //Remove all listeners on the window
        frame.addKeyListener(keyboardHandler);
    }
}