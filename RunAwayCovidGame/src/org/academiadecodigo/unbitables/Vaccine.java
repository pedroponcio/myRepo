package org.academiadecodigo.unbitables;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Vaccine implements GameObjects{

    private Rectangle vaccineHitBox;
    private Picture vaccinePicture;
    private int immunityCounter, index;

    public Vaccine (int index) {
        this.index = index;
        int x = (int) (Math.random() * 48*Background.CELLSIZE + Background.LEFTPADDING);
        int y = (int) (Math.random() * 48*Background.CELLSIZE + Background.TOPPADDING);
        vaccineHitBox = new Rectangle(x, y, 25, 25);
        //vaccineHitBox.setColor(Color.YELLOW);
        //vaccineHitBox.fill();
        vaccinePicture = new Picture(x,y,"vaccine.png");
        vaccinePicture.draw();
        immunityCounter = 10;
    }

    public void immunity(Player player) {
        player.makeImmunityEllipse();
        player.setImmunity(true);
        vaccineHitBox.delete();
        vaccinePicture.delete();
    }

    @Override
    public Rectangle getHitBox() {
        return vaccineHitBox;
    }
}
