package org.academiadecodigo.unbitables;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Mask implements GameObjects {

    private Rectangle maskHitBox;
    private Picture maskPicture;
    private int life, index;

    public Mask (int index) {
        this.index = index;
        int x = (int) (Math.random() * 48*Background.CELLSIZE + Background.LEFTPADDING);
        int y = (int) (Math.random() * 48*Background.CELLSIZE + Background.TOPPADDING);
        maskHitBox = new Rectangle(x, y, 25, 25);
        //maskHitBox.setColor(Color.GREEN);
        //maskHitBox.fill();
        maskPicture = new Picture(x,y,"mask.png");
        maskPicture.draw();
        life = 1;
    }

    public void heal(Player player) {
        if (player.isColliding()) {
            if (player.getHealth() < 3) {
                player.heal();
            }
            maskHitBox.delete();
            maskPicture.delete();
            player.setColliding(false);
        }
    }

    @Override
    public Rectangle getHitBox() {
        return maskHitBox;
    }
}
