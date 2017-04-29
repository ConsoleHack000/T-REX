package net.net16.aregsroom.trexclone.states;

import net.net16.aregsroom.trexclone.Utils;
import net.net16.aregsroom.trexclone.gameobject.Cactus;
import net.net16.aregsroom.trexclone.gameobject.Dino;
import net.net16.aregsroom.trexclone.gameobject.GroundTile;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by areg on 4/26/17.
 */
public class StateGame extends BasicGameState {

    public ArrayList<GroundTile> tiles = new ArrayList<GroundTile>();
    public ArrayList<Cactus> cacti = new ArrayList<Cactus>();
    public float ttwfnt;
    public float ticksWaited;
    public Random r = new Random();
    public float ticksToWaitForNextCactus = Utils.gameSpeed * 10;

    public Dino dino;

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_G) {
            Utils.gameState = 1;
        } else if (key == Input.KEY_UP) {
            dino.jump();
        } else if (key==Input.KEY_DOWN){
            dino.down();
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
        Utils.ground = new Image(this.getClass().getResourceAsStream("/ground.png"), "wtf", false);
        try {
            Utils.cacti = new SpriteSheet(this.getClass().getResource("/cacti.png"), 64, 64);
            Utils.dino_stand = new SpriteSheet(this.getClass().getResource("/dino-walk.png"), 64, 64);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dino = new Dino();

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        if (Utils.gameState == 0) {
            graphics.drawString("Press G to start", 300, 50);
        }
        renderObjects(gameContainer, stateBasedGame, graphics);
        if (Utils.gameState == 1) dino.render(gameContainer, stateBasedGame, graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        updateObjects(gameContainer,stateBasedGame,i);
        ttwfnt = 32/Utils.gameSpeed;
        ticksWaited+=1;
        if(ttwfnt-ticksWaited<1){
            tiles.add(new GroundTile());
            ticksWaited = 0;
        }
        System.out.println(ticksToWaitForNextCactus);
        if(ticksToWaitForNextCactus--<=0){

             ticksToWaitForNextCactus = r.nextInt(40)+40/(Utils.gameSpeed/4);

             cacti.add(new Cactus(r));

        }
        Utils.gameSpeed+=0.002;
        if(Utils.gameState==1)dino.update(gameContainer,stateBasedGame,i);
    }

    public void updateObjects(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        Iterator<GroundTile> tileIterator = tiles.iterator();
        while (tileIterator.hasNext()) {
            GroundTile t = tileIterator.next(); // must be called before you can call i.remove()
            if(t.dead)
                tileIterator.remove();
            else t.update(gameContainer,stateBasedGame,i);
        }
        Iterator<Cactus> cactusIterator = cacti.iterator();
        while (cactusIterator.hasNext()) {
            Cactus t = cactusIterator.next(); // must be called before you can call i.remove()
            if(t.dead)
                cactusIterator.remove();
            else t.update(gameContainer,stateBasedGame,i);
        }
    }


    private void renderObjects(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        for(GroundTile tile : tiles){
            tile.render(gameContainer,stateBasedGame,graphics);
        }
        for(Cactus cactus : cacti){
            cactus.render(gameContainer,stateBasedGame,graphics);
        }
    }
}
