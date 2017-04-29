package net.net16.aregsroom.trexclone;

import org.newdawn.slick.AppGameContainer;

/**
 * Created by areg on 4/26/17.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Utils.gameSpeed=3;
            AppGameContainer appgc = new AppGameContainer(new Game());
            appgc.setDisplayMode(850,300,false);
            appgc.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
