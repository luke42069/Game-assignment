
package DiamondHunter;

import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class CollectItem extends GameObject{

    int xMove, yMove;
    
    public CollectItem(int x, int y, String imgname) throws SlickException {
        super(x, y, imgname);
        xMove = (int)(Math.random()*2+1)%2==0? -1:1;
        yMove = (int)(Math.random()*2+1)%2==0? -1:1;
    }
    
    //exclusive method
    public void place(ArrayList<Rectangle> barriers){
        boolean hitting;
        //put all objects on randomly board within the paths
        do{
        hitting=false;
        hitbox.setX((int)(Math.random()*760));
        hitbox.setY((int)(Math.random()*600));
            for (Rectangle barrier : barriers) {
                if(hitbox.intersects(barrier)){
                    hitting=true;
                }
            }
            
    }while(hitting);
    }

    @Override
    public void move(ArrayList<Rectangle> barriers) {
        int xloc = (int)hitbox.getX();
        int yloc =(int)hitbox.getY();
        xloc += xMove;
        yloc+=yMove;
        
        //make sure x move keeps item in bounds
        hitbox.setX(xloc);
        boolean hitting = false;
        for (Rectangle barrier : barriers) {
            if(hitbox.intersects(barrier)||xloc<10||xloc>780){
                hitting=true;
            }
        }
        if(hitting){//reset xloc reverse xmove
            xMove*=-1;
            xloc+=xMove;
            hitbox.setX(xloc);
        }
        
        //check y for same
        hitbox.setY(yloc);
        
        for (Rectangle barrier : barriers) {
            if(hitbox.intersects(barrier)||yloc<10||yloc>620){
                hitting=true;
            }
        }
        if(hitting){//reset xloc reverse xmove
            yMove*=-1;
            yloc+=xMove;
            hitbox.setY(yloc);
        }
    }
    
}
