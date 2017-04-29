package net.net16.aregsroom.trexclone.gameobject;

import net.net16.aregsroom.trexclone.Utils;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.util.Random;

/**
 * Created by areg on 4/26/17.
 */
public class GroundTile extends GameObject {

    float x = 850;
    public boolean dead = false;
    public GroundTile(){

    }
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        graphics.drawImage(Utils.ground,x,225);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        if(Utils.gameStarted)x-=Utils.gameSpeed;
        if(x< (-32)){
            dead = true;
        }
    }
}
