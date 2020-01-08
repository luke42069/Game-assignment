
package DiamondHunter;

import AsteroidGame.Maingame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameLauncher extends StateBasedGame{

    public GameLauncher(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        //add states in order they should appear
        //3 separate classses that extend basic game state
        this.addState(new IntroScreen());
        this.addState(new MainGame());
        this.addState(new GameOver());
    }
    
    public static void main(String args[]) throws SlickException{
        GameLauncher game = new GameLauncher ("Diamond Hunter");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800,640,false);
        app.setShowFPS(false);
        app.setTargetFrameRate(200);
        app.start();
    }
    
}
