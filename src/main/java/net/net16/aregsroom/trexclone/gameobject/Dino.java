package net.net16.aregsroom.trexclone.gameobject;

import net.net16.aregsroom.trexclone.Utils;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by areg on 4/27/17.
 */
public class Dino extends GameObject {

    public float y;
    public float motX, motY;
    int leftToChange=0;
    int ticksToChange=10;
    boolean texturePhase;
    boolean crouching;

    public Dino(){
        this.y = 225-64;

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        if(!crouching){
            graphics.drawImage(Utils.dino_stand.getSprite(texturePhase?1:0,0),100,y);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        y+=motY;
        y = Utils.clamp(y,0,225-64);
        motY+=0.7;
        if(!crouching){
            if(++leftToChange==ticksToChange){
                texturePhase=!texturePhase;
                leftToChange=0;
            }

        }
    }
    public boolean jump(){
        if(y==225-64) {
            motY = -10;
            return true;
        }
        return false;
    }
}
