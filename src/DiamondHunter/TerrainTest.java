
package DiamondHunter;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class TerrainTest extends BasicGame{

    Terrain map;
    
    public TerrainTest(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        map = new Terrain();
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        map.draw();
        map.drawGrid(g);
    }
    
}
