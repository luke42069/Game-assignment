package DiamondHunter;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainGame extends BasicGameState {

    int timer = 0;
    Guy guy;
    ArrayList<GameObject> diamonds = new ArrayList();
    Terrain terrain;
    GameObject house;
    GameObject ghost, ghost2;
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
       guy = new Guy(0,170);
       ghost = new Ghost(100,170,"images/ghost.png");
       ghost2 = new Ghost(700,170,"images/ghost.png");
       house = new CollectItem(280,400,"images/house.png");
       terrain = new Terrain();
       //scatter 10 diamonds on paths
        for (int i = 0; i < 10; i++) {
            GameObject d = new CollectItem(0,0,"images/diamond.png");
            ((CollectItem)d).place(terrain.getBarriers());
            diamonds.add(d);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException { 
        //move guy with arrow keys
        Input in = gc.getInput();
        guy.move(in,terrain.getBarriers());
        timer++;
        //hit test guy with sdiamonds
        for (int j = 0; j < diamonds.size(); j++) {
            if(guy.isHitting(diamonds.get(j))){
                diamonds.remove(j);
                //if we have all diamonds open exit road
                if(diamonds.isEmpty()&&terrain.hasExitRoad()==false){
                    terrain.addExitRoad();
                }
            }
        }
        
        //guy hitting ghost or house (game over either way)
        if(guy.isHitting(ghost)||guy.isHitting(ghost2)){
            GameOver.setMessage("You were captured by the ghost - YOU LOSE!",200);
            sbg.enterState(2, new FadeOutTransition(),new FadeInTransition());
        }
        else if(guy.isHitting(house)){
            GameOver.setMessage("You got all the diamonds and found your way home - you WIN!",100);
            sbg.enterState(2,new FadeOutTransition(),new FadeInTransition());
        }
        
        //moving ghost and diamonds
        if(timer==5){
            for (GameObject diamond : diamonds) {
                diamond.move(terrain.getBarriers());
            }
            timer=0;
        }
        if(timer%2==0){
            ghost.move(terrain.getBarriers());
            ghost2.move(terrain.getBarriers());
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
       terrain.draw();
       if(terrain.hasExitRoad())
           house.draw();
       guy.draw();
        for (GameObject diamond : diamonds) {
            diamond.draw();
        }
        ghost.draw();
        ghost2.draw();
    }
    
    public int getID() {
       return 1;  //this id will be different for each screen
    }

    
}