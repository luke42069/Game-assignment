package AsteroidGame;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;


public class Asteroid {
    //instance variables
    private Image image;
    private Rectangle hitbox;
    private boolean isChosen;
    //controls rock movement
    private int xdir,ydir;
    //class level variable
    private static int GAME_WIDTH;
    private static int GAME_HEIGHT;
    private TrueTypeFont font;
    
    public Asteroid(int x, int y)throws SlickException{
        image = new Image("images/astroid.png");
        hitbox = new Rectangle(x,y,image.getWidth(),image.getHeight());
        font = new TrueTypeFont(new java.awt.Font("Impact", 0, 18),true);
        
        
        //move asteroid in random direction
        while(true){
            xdir = (int)(Math.random()*7)-3;
            ydir = (int)(Math.random()*7)-3;
            if(xdir!=0&&ydir!=0)break;
        }
        isChosen = false; //can only cllick on chosen rock
    }
    
    public static void setGameSize(int x, int y){
        GAME_WIDTH = x;
        GAME_HEIGHT = y;
    }
    
    public void setChosen(){
        isChosen=true;
    }
    
    public boolean isChosen(){
        return isChosen;
    }
    
    public void move(){
        //apply x&y dir to hitbox
        hitbox.setX(hitbox.getX()+xdir);
        hitbox.setY(hitbox.getY()+ydir);
        
        //if we hit a wall, change direction
        if(hitbox.getX()<=0||hitbox.getX()>=GAME_WIDTH)
            xdir=-xdir;
        if(hitbox.getY()<=0||hitbox.getY()>=GAME_HEIGHT)
            ydir=-ydir;
    }
    
    public boolean isHit(int mx,int my){
        return hitbox.contains(mx,my);
    }
    
    public void draw(){
        if(isChosen)
            image.setColor(1,200,0,0,1f);
        image.draw(hitbox.getX(),hitbox.getY());
    }
}
