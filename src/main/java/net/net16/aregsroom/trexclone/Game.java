package net.net16.aregsroom.trexclone;

import net.net16.aregsroom.trexclone.states.StateGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by areg on 4/26/17.
 */
public class Game extends StateBasedGame{
    public Game() {
        super("Mr. T-Rex");
        addState(new StateGame());
    }

    public void initStatesList(GameContainer gc) throws SlickException {
        getState(0).init(gc,this);
        enterState(0);
    }
}
