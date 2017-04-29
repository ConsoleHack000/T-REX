package net.net16.aregsroom.trexclone.states;

import net.net16.aregsroom.trexclone.Utils;
import net.net16.aregsroom.trexclone.gameobject.Dino;
import net.net16.aregsroom.trexclone.gameobject.GroundTile;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by areg on 4/26/17.
 */
public class StateGame extends BasicGameState{

    public ArrayList<GroundTile> tiles = new ArrayList<GroundTile>();
    public float ttwfnt;
    public float ticksWaited;
    public Dino dino;
    @Override
    public void keyPressed(int key, char c) {
        if(key == Input.KEY_G){
            Utils.gameStarted = true;
        }
        else if (key==Input.KEY_UP){
            dino.jump();
        }
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        gameContainer.getInput().addKeyListener(this);
        tiles.add(new GroundTile());
        Utils.ground = new Image(this.getClass().getResourceAsStream("/ground.png"), "wtf",false);
        try {
            Utils.cacti = new SpriteSheet(this.getClass().getResource("/cacti.png"),64,64);
            Utils.dino_stand = new SpriteSheet(this.getClass().getResource("/dino-walk.png"),64,64);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dino = new Dino();

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        if(!Utils.gameStarted){
            graphics.drawString("Press G to start",300,50);
        }
        for(GroundTile e : tiles){
            e.render(gameContainer,stateBasedGame,graphics);
        }
        dino.render(gameContainer,stateBasedGame,graphics);
        graphics.drawImage(Utils.cacti.getSprite(3,0),50,50);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Iterator<GroundTile> e = tiles.iterator();
        while (e.hasNext()) {
            GroundTile t = e.next(); // must be called before you can call i.remove()
            if(t.dead)
            e.remove();
            else t.update(gameContainer,stateBasedGame,i);
        }
        ttwfnt = 32/Utils.gameSpeed;
        ticksWaited+=1;
        if(ttwfnt-ticksWaited<1){
            tiles.add(new GroundTile());
            ticksWaited = 0;
        }
        dino.update(gameContainer,stateBasedGame,i);
    }
}
