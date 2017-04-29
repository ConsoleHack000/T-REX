package net.net16.aregsroom.trexclone.gameobject;

import net.net16.aregsroom.trexclone.Utils;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.util.Random;

/**
 * Created by areg on 4/29/17.
 */
public class Cactus extends GameObject {

    public float x = 850;
    public int textureID;
    public boolean dead;

    public Cactus(Random r) {

        textureID = r.nextInt(4);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        graphics.drawImage(Utils.cacti.getSprite(textureID, 0), x, 225 - 64);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        x -= Utils.gameSpeed;
        if (x < (-64)) {
            dead = true;
        }
    }
}
