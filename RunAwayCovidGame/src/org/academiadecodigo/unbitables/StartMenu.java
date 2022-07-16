package org.academiadecodigo.unbitables;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class StartMenu {

    SoundManager soundManager = new SoundManager();
    private Picture startBackground;
    private Game game;
    private KeyboardLogic keyboardLogic;
    private boolean isSkipped = false;
    //private final int delay = 50;

    /*public StartMenu() {
        keyboardLogic.setStartMenu(this);
        keyboardLogic.init();
    }*/

    public StartMenu() {
        keyboardLogic = new KeyboardLogic();
        /*this.game = new Game();*/

        this.keyboardLogic.setStartMenu(this);
        this.keyboardLogic.init();
    }
    public void init() {
        startBackground();
        SoundManager.getSound("menu").setLoop(20);
        //thread1.start();
    }
    public void delete() {
        if (isSkipped) {
            return;
        }
        startBackground.delete();
        SoundManager.getSound("menu").close();
        Game game = new Game();
        isSkipped = true;
    }
    public void startBackground() {
        startBackground = new Picture(10, 10, "startMenu.png");
        startBackground.draw();
        System.out.println();
    }
}
