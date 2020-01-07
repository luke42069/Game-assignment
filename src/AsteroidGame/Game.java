package AsteroidGame;


import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class Game extends BasicGame {

    private ArrayList<Asteroid> rocks;
    private int timer1=0;
    private TrueTypeFont font;
    private double timer2=0;

    public Game(String title) {
        super(title);
    }

    public void init(GameContainer gc) throws SlickException {
        rocks = new ArrayList();
        for (int i = 0; i < 3; i++) {
            int xloc = (int) (Math.random() * 750);
            int yloc = (int) (Math.random() * 550);
            rocks.add(new Asteroid(xloc, yloc));
        }
        Asteroid.setGameSize(800, 600);
        rocks.get((int) (Math.random() * rocks.size())).setChosen();
        font = new TrueTypeFont(new java.awt.Font("Helvetica", 0, 18),true);
    }

    public void update(GameContainer gc, int i) throws SlickException {
        if(rocks.size()>0){
        timer2++;
        timer1++;}
        if(timer1==500){
            timer1=0;
            if(rocks.size()==0)return;
            int xloc = (int) (Math.random() * 750);
            int yloc = (int) (Math.random() * 550);
            rocks.add(new Asteroid(xloc, yloc));
        }
        Input in = gc.getInput();
        int mx = in.getMouseX();
        int my = in.getMouseY();

        for (Asteroid a : rocks) {
            a.move();
        }

        if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            //go through rocks to see if any hit
            for (int j = 0; j < rocks.size(); j++) {
                if (rocks.get(j).isHit(mx, my) && rocks.get(j).isChosen()) {
                    rocks.remove(j);
                    if(rocks.size()>0)pickrock();
                    break;
                }
            }
        }
    }

    public void pickrock() {
        int x = (int) (Math.random() * rocks.size());
        rocks.get(x).setChosen();
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        for (Asteroid a : rocks) {
            a.draw();
        }
        g.setColor(new Color(0,100,0));
        g.fill(new Rectangle(0,0,800,40));
        font.drawString(10,10,"Rocks to go: "+rocks.size(),Color.white);
        font.drawString(700,10,"Time: "+timer2/100,Color.white);
    }

    public static void main(String args[]) throws SlickException {
        Game game = new Game("Asteroid Hunter");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(100);
        app.start();
    }

}
