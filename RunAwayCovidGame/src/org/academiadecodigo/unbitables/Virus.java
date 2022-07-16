package org.academiadecodigo.unbitables;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Virus implements GameObjects {
    private Picture pictureVirus;
    private Rectangle virusHitBox;
    private final int speed = 20;
    private final int directionChangeLevel = 5;

    private Integer index;

    private Direction currDirection;

    public Virus (Integer index) {
        this.index = index;
        int x = (int) (Math.random() * 40*Background.CELLSIZE + Background.LEFTPADDING);
        int y = (int) (Math.random() * 40*Background.CELLSIZE + Background.TOPPADDING);
        virusHitBox = new Rectangle(x, y, 25, 25);
        pictureVirus = new Picture(x, y, "virus.png");
        pictureVirus.grow(-15,-15);
        pictureVirus.draw();
        /*virusHitBox.setColor(Color.RED);
        virusHitBox.fill();
        biggerHitBox = new Rectangle(virusHitBox.getX(), virusHitBox.getY(), 20, 20);*/
        currDirection = Direction.values()[(int) (Math.random()*Direction.values().length)];
    }

    public int getIndex() {
        return index;
    }


    /*public boolean intersects(org.academiadecodigo.simplegraphics.graphics.Rectangle r) {
        int tw = virusHitBox.getWidth();
        int th = virusHitBox.getHeight();
        int rw = r.getWidth();
        int rh = r.getHeight();
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        int tx = virusHitBox.getX();
        int ty = virusHitBox.getY();
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
    }*/

    public void delete() {
        virusHitBox.delete();
        pictureVirus.delete();
    }

    public Direction chooseDirection() {

        Direction newDirection = currDirection;

        if (Math.random() > ((double) directionChangeLevel) / 10) {
            newDirection = Direction.values()[(int) (Math.random() * Direction.values().length)];

            if (newDirection.isOpposite(currDirection)) {
                return chooseDirection();
            }
        }

        return newDirection;

    }

    public void move(Direction direction) {

        switch (direction) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case RIGHT:
                moveRight();
                break;
            case LEFT:
                moveLeft();
                break;
        }
    }

    public Direction getCurrDirection() {
        return currDirection;
    }

    public void moveRight() {
        for (int i=0; i<speed; i++) {
            if(pictureVirus.getX()+52 > 50*15+10) {
            currDirection = currDirection.oppositeDirection();
            return;
        } else {
                virusHitBox.translate(1,0);
                pictureVirus.translate(1,0);
            }
        }
    }
    public void moveLeft() {
        for (int i=0; i<speed; i++) {
            if(pictureVirus.getX()-1 < 10) {
                currDirection = currDirection.oppositeDirection();
                return;
            } else {
                virusHitBox.translate(-1,0);
                pictureVirus.translate(-1,0);
            }
        }
    }
    public void moveUp() {
        for (int i=0; i<speed; i++) {
            if(pictureVirus.getY()-1 < 40) {
                currDirection = currDirection.oppositeDirection();
                return;
            } else {
                virusHitBox.translate(0,-1);
                pictureVirus.translate(0,-1);
            }
        }
    }
    public void moveDown() {
        for (int i=0; i<speed; i++) {
            if(pictureVirus.getY()+45 > 50*15+40) {
                currDirection = currDirection.oppositeDirection();
                return;
            } else {
                virusHitBox.translate(0,1);
                pictureVirus.translate(0,1);
            }
        }
    }

    @Override
    public Rectangle getHitBox() {
        return virusHitBox;
    }

    public void damage(Player player) {
        if (player.isColliding()) {
            player.damage();
            virusHitBox.delete();
            pictureVirus.delete();
            player.setColliding(false);
        }
    }
}
