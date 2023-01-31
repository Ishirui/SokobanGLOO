package View;

import static java.util.Map.entry;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Model.Drawable;

public class LevelPanel extends JPanel {
    private Drawable[] drawables;
    private Drawable[] overlayDrawables; //Things to draw ON TOP of the rest: targets, menu maybe ?

    private int objectSize;
    private Map<String, Image> imageDict = new HashMap<String, Image>();

    //Declare as static to not have to reload on every instanciation of LevelView
    private static Map<String, Image> originalImageDict = null;
    static{ //We need to do this weird thing instead of just assigning a default to imageDict to allow us to handle the IOError
        try {
            originalImageDict = 
            Map.ofEntries(
                entry("Floor", ImageIO.read(LevelView.class.getResource("assets/Floor.png"))),
                entry("Box", ImageIO.read(LevelView.class.getResource("assets/Box.png"))),
                entry("Player", ImageIO.read(LevelView.class.getResource("assets/Player.png"))),
                entry("Target", ImageIO.read(LevelView.class.getResource("assets/Target.png"))),
                entry("Wall", ImageIO.read(LevelView.class.getResource("assets/Wall.png")))
            );
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public LevelPanel(Drawable[] drawables, Drawable[] overlayDrawables, int objectSize){
        super();
        for(String key:originalImageDict.keySet()){ //Scale all image instances in the set
            imageDict.put(key, originalImageDict.get(key).getScaledInstance(objectSize, objectSize, objectSize));
        }

        this.drawables = drawables;
        this.overlayDrawables = overlayDrawables;
        this.objectSize = objectSize;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        for(Drawable obj:drawables){ //Draw main drawables
            int x = objectSize*obj.getColumn();
            int y = objectSize*obj.getRow();
            
            Image image = imageDict.get(obj.getClass().getSimpleName());
            
            g.drawImage(image, x, y, null);
        } 

        for(Drawable obj:overlayDrawables){ //Draw overlay drawables
            int x = objectSize*obj.getColumn();
            int y = objectSize*obj.getRow();
            
            Image image = imageDict.get(obj.getClass().getSimpleName());
            
            g.drawImage(image, x, y, null);
        } 
    }
}
