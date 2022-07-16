package org.academiadecodigo.unbitables;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class KeyboardLogic implements KeyboardHandler {
    private Player player;
    private StartMenu startMenu;
    private Keyboard keyboard;

    private KeyboardEvent up, down, left, right, rightR, leftR, upR, downR,space;

    boolean upPressed = false;
    boolean downPressed = false;
    boolean leftPressed = false;
    boolean rightPressed = false;
    boolean spacePressed = false;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setStartMenu(StartMenu startMenu) {
        this.startMenu = startMenu;
    }

    public void init(){
        keyboard = new Keyboard(this);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent space = new KeyboardEvent();
        space.setKey(KeyboardEvent.KEY_SPACE);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(right);
        keyboard.addEventListener(left);
        keyboard.addEventListener(up);
        keyboard.addEventListener(down);
        keyboard.addEventListener(space);

    }
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (player != null)
            switch (keyboardEvent.getKey()) {
                case KeyboardEvent.KEY_LEFT:
                    player.moveLeft();
                    break;
                case KeyboardEvent.KEY_RIGHT:
                    player.moveRight();
                    break;
                case KeyboardEvent.KEY_UP:
                    player.moveUp();
                    break;
                case KeyboardEvent.KEY_DOWN:
                    player.moveDown();
                    break;
            } else {

            if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
                startMenu.delete();
            }
        }
    }
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }

}
