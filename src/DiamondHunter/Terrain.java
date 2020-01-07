
package DiamondHunter;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;


public class Terrain {
    
    private SpriteSheet grass;
    private boolean roadOn;
    private ArrayList<Rectangle>barriers = new ArrayList<Rectangle>();
    
    public Terrain() throws SlickException{
        grass = new SpriteSheet("images/grass.png",32,32);
        //barriers based on grid
        barriers.add(new Rectangle(0,0,320,160));
        barriers.add(new Rectangle(384,0,32*13,160));
        barriers.add(new Rectangle(0,224,32*3,32*13));
        barriers.add(new Rectangle(576,224,32*7,32*13));
        //this one goes later when exit road appears and 4 new ones surrounding road are added
        barriers.add(new Rectangle(160,224,32*11,32*13));
    }
    
    public void draw(){
        //480 = 15 rows of 32 pixels
        grass.startUse();
        //top grass
        Image lawn = grass.getSprite(0,0);
        Image path = grass.getSprite(1,2);
        Image stone = grass.getSprite(6,5);
        
        //rows 1 to 5 - y goes 0 to 160 (32x5)
        for (int x = 0; x < 800;x+=32) {
            for (int y = 0; y < 160; y+=32) {
                if(x == 320 || x ==352){
                    path.draw(x,y);
                }
                else{
                    lawn.draw(x,y);
                }
            }
        }
        
        //row 6 and 7 - y goes 160 to 224
        for (int x = 0; x < 800; x+=32) {
            for (int y = 160; y < 224; y+=32) {
                path.draw(x,y);
            }
        }
        
        //rows 8 to the end, ygoes  224 to 640
        for (int x = 0; x < 800; x+=32) {
            for (int y = 224; y < 640; y+=32) {
                //2 vertical paths exist at x 96-128 and 512-544
                if(x==96||x==128||x==512||x==544){
                    path.draw(x,y);
                }
                else{
                    lawn.draw(x,y);
                }
            }
        }
        //draw exitRoad if needed
        if(roadOn){
            for (int i = 160; i <= 288; i+=32) {
                stone.draw(i,384);
            }
            stone.draw(256,416);
            stone.draw(288,416);
        }
        grass.endUse();
    }
    
    public void drawGrid(Graphics g){
        g.setColor(new Color(200,200,200));
        TrueTypeFont ttf = new TrueTypeFont(new java.awt.Font("Arial",0,10),true);
        for (int i = 0; i < 32*25; i+=32) {
            for (int j = 0; j < 32*20; j+=32) {
                Rectangle box = new Rectangle(i,j,32,32);
                g.draw(box);
                ttf.drawString(i+3,j+3,""+i,Color.white);
                ttf.drawString(i+3,j+12,""+j,Color.white);
            }
            
        }
        g.setColor(Color.red);
        for (Rectangle barrier : barriers) {
            g.draw(barrier);
        }
    }
    
    public void addExitRoad(){
        roadOn=true;
        barriers.remove(4);
        barriers.add(new Rectangle(160,224,32*11,32*5-3));
        barriers.add(new Rectangle(320,384,32*6,32*8));
        barriers.add(new Rectangle(256,448,32*2,32*6));
        barriers.add(new Rectangle(160,416+3,32*3,32*7-3));
    }
    
    public boolean hasExitRoad(){
        return roadOn;
    }
    
    public ArrayList<Rectangle> getBarriers(){
        return barriers;
    }
    
}
