package Interface;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Modele.Drawable;

public class DrawHandler extends JPanel {

    private Map<String, Image> imageDict = new HashMap<String, Image>();
    private Drawable[] drawables;

    private int blockSize = 128; //The images are 128x128

    public DrawHandler(Drawable[] drawables) throws IOException {
        super();

        //Load all images
        System.out.println(this.getClass().getResource("assets/Floor.png"));

        imageDict.put("Floor", ImageIO.read(getClass().getResource("assets/Floor.png")));
        imageDict.put("Box", ImageIO.read(getClass().getResource("assets/Box.png")));
        imageDict.put("Player", ImageIO.read(getClass().getResource("assets/Player.png")));
        imageDict.put("Target", ImageIO.read(getClass().getResource("assets/Target.png")));
        imageDict.put("Wall", ImageIO.read(getClass().getResource("assets/Wall.png")));

        this.drawables = drawables;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        for(Drawable obj:drawables){
            int x = blockSize*obj.getColumn();
            int y = blockSize*obj.getRow();
            
            Image image = imageDict.get(obj.getClass().getSimpleName());
            
            g.drawImage(image, x, y, null);
        }
        
        
    }
}
