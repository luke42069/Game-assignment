
package DiamondHunter;

import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public  class Ghost extends GameObject{

    int xdir,ydir;
    public static final int HALL1 = 110,HALL2=330, HALL3=525;
    public static final int MAIN_HALL = 170;
    public static final int UP = 1, DOWN = 1, LEFT=1, RIGHT=1,STOP=0;
    
    
    public Ghost(int x, int y, String imgname) throws SlickException {
        super(x, y, imgname);
        xdir = RIGHT;
        ydir = STOP;
        hitbox.setWidth(image.getWidth());
        hitbox.setHeight(image.getHeight());
    }

    @Override
    public void move(ArrayList<Rectangle> barriers) {
        int x = (int)hitbox.getX();
        int y = (int)hitbox.getY();
        
        if(y>=600||y<=0)
            ydir*=-1;
        
        if(x>=760||x<=0)
            xdir*=-1;
        
        //go down lower hall from main
        if(xdir==RIGHT&&(x==HALL1||x==HALL3)){
            xdir=STOP;
            ydir=DOWN;
        }
        
        //come back to main from a lower hall
        if(y==MAIN_HALL&&ydir==UP){
            ydir=STOP;
            xdir=RIGHT;
        }
        
        //up second from main while going left
        if(x==HALL2&&xdir==LEFT){
            y--;
            xdir=STOP;
            ydir=UP;
        }
        //back to main from upper hall
        if(y==MAIN_HALL&&ydir==DOWN&&x==HALL2){
            ydir=STOP;
            xdir=LEFT;
        }
        
        x+=xdir;
        y+=ydir;
        
        hitbox.setY(y);
        hitbox.setX(x);
    }
    
}
