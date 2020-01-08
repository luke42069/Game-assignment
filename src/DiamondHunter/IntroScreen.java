package DiamondHunter;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class IntroScreen extends BasicGameState {

    private Image mainImg;

    
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            //go to next screen (id =1) wghen mouse is clicked and fade
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        mainImg.draw(0, 0);
    }

    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
