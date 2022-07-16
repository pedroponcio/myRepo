package org.academiadecodigo.unbitables;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import sound.Sound;

public class Background {

    SoundManager soundManager = new SoundManager();

    public static int LEFTPADDING = 10;
    public static int TOPPADDING = 40;
    public static int CELLSIZE = 15;
    public static int COLS = 50;
    public static int ROWS = 50;
    private Rectangle gameField;
    private Picture pictureBackground;
    private Rectangle topBox;

    public Background() {
        gameField = new Rectangle(LEFTPADDING,TOPPADDING, COLS*CELLSIZE, ROWS*CELLSIZE);
        pictureBackground = new Picture(LEFTPADDING,10, "Arena.png");
        pictureBackground.draw();
        topBox = new Rectangle(LEFTPADDING, LEFTPADDING, COLS*CELLSIZE, TOPPADDING - LEFTPADDING);
        //gameField.draw();
        //topBox.draw();
        SoundManager.getSound("background").setLoop(10);
    }

    public Rectangle getGameField() {
        return gameField;
    }
}
