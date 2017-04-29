package net.net16.aregsroom.trexclone;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by areg on 4/26/17.
 */
public class Utils {
    /**
     * 0 for not started,
     * 1 for playing,
     * 2 for lost**/
    public static int gameState;
    public static float gameSpeed;
    public static Image ground;
    public static SpriteSheet dino_stand;
    public static SpriteSheet dino_crouch;
    public static SpriteSheet cacti;

    public static float clamp(float val, float min, float max){
        return Math.max(min, Math.min(max, val));
    }

}
