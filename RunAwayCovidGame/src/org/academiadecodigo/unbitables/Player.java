package org.academiadecodigo.unbitables;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player {

    private int health = 3;

    private Picture player;
    private Rectangle playerHitBox;
    private Ellipse immunityEllipse;
    private Position position;
    private KeyboardLogic keyboardLogic;
    private final int speed = 3;
    private boolean rightIndex = true;
    private int immunityCounter = 0;
    private boolean immune, colliding;

    public Player(Background background) {
        int x = 375 + Background.LEFTPADDING;
        int y = 375 + Background.TOPPADDING;
        playerHitBox = new Rectangle(x, y, 25, 25);
        position = new Position(playerHitBox.getX() * Background.CELLSIZE + Background.LEFTPADDING, playerHitBox.getY() * Background.CELLSIZE + Background.TOPPADDING);
        player = new Picture(x, y, "right.png");
        player.draw();
        /*playerHitBox.setColor(Color.BLUE);
        playerHitBox.fill();*/
    }

    public boolean isImmune() {
        return immune;
    }

    public void decreaseImmunityCounter() {
        immunityCounter--;
    }

    public void setImmunityCounter(int immunityCounter) {
        this.immunityCounter = immunityCounter;
    }

    public int getImmunityCounter() {
        return immunityCounter;
    }

    public void makeImmunityEllipse() {
        this.immunityEllipse = new Ellipse(playerHitBox.getX(), playerHitBox.getY(), 40, 40);
        immunityEllipse.setColor(Color.YELLOW);
        immunityEllipse.draw();
    }

    public void setColliding(boolean colliding) {
        this.colliding = colliding;
    }

    public boolean isColliding() {
        return colliding;
    }

    public void heal() {
        health++;
    }

    public void setImmunity(boolean immune) {
        this.immune = immune;
        if (immune == false && immunityEllipse != null) {
            immunityEllipse.delete();
        }
    }

    public int getHealth() {
        return health;
    }

    public void damage() {
        this.health--;
    }

    public Rectangle getPlayerHitBox() {
        return playerHitBox;
    }

    public boolean intersects(org.academiadecodigo.simplegraphics.graphics.Rectangle r) {
        int tw = playerHitBox.getWidth();
        int th = playerHitBox.getHeight();
        int rw = r.getWidth();
        int rh = r.getHeight();
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        int tx = playerHitBox.getX();
        int ty = playerHitBox.getY();
        int rx = r.getX();
        int ry = r.getY();
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }

    public void moveRight() {
        int initialCol = position.getCol();
        int initialRow = position.getRow();
        for (int i = 0; i < speed; i++) {
            position.moveRight(1);
            int dx = (position.getCol()) - (initialCol);
            int dy = (position.getRow()) - (initialRow);
            if (player.getX() + Background.CELLSIZE + 23 > 50 * Background.CELLSIZE + Background.LEFTPADDING) return;
            playerHitBox.translate(dx, dy);
            player.translate(dx, dy);
            if (immunityEllipse != null) {
                immunityEllipse.translate(dx, dy);
            }
        }
        if (rightIndex) {
            player.load("right.png");
            rightIndex = false;
        } else {
            player.load("right1.png");
            rightIndex = true;
        }
    }

    public void moveLeft() {
        int initialCol = position.getCol();
        int initialRow = position.getRow();
        for (int i = 0; i < speed; i++) {
            position.moveLeft(1);
            int dx = (position.getCol()) - (initialCol);
            int dy = (position.getRow()) - (initialRow);
            if (player.getX() - dx <= Background.LEFTPADDING + 3) return;
            playerHitBox.translate(dx, dy);
            player.translate(dx, dy);
            if (immunityEllipse != null) {
                immunityEllipse.translate(dx, dy);
            }
        }
        if (rightIndex) {
            player.load("left.png");
            rightIndex = false;
        } else {
            player.load("left1.png");
            rightIndex = true;
        }
    }

    public void moveUp() {
        int initialCol = position.getCol();
        int initialRow = position.getRow();
        for (int i = 0; i < speed; i++) {
            position.moveUp(1);
            int dx = (position.getCol()) - (initialCol);
            int dy = (position.getRow()) - (initialRow);
            if (player.getY() - dy <= Background.TOPPADDING + 3) return;
            playerHitBox.translate(dx, dy);
            player.translate(dx, dy);
            if (immunityEllipse != null) {
                immunityEllipse.translate(dx, dy);
            }
        }
        if (rightIndex) {
            player.load("up.png");
            rightIndex = false;
        } else {
            player.load("up1.png");
            rightIndex = true;
        }
    }

    public void moveDown() {
        int initialCol = position.getCol();
        int initialRow = position.getRow();
        for (int i = 0; i < speed; i++) {
            position.moveDown(1);
            int dx = (position.getCol()) - (initialCol);
            int dy = (position.getRow()) - (initialRow);
            if (player.getY() + Background.CELLSIZE + 23 > 50 * Background.CELLSIZE + Background.TOPPADDING) return;
            playerHitBox.translate(dx, dy);
            player.translate(dx, dy);
            if (immunityEllipse != null) {
                immunityEllipse.translate(dx, dy);
            }
        }
        if (rightIndex) {
            player.load("down.png");
            rightIndex = false;
        } else {
            player.load("down1.png");
            rightIndex = true;
        }
    }
}
