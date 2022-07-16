package org.academiadecodigo.unbitables;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Game {

    SoundManager soundManager = new SoundManager();
    private Background background;
    private Player player;
    private Map<Integer, GameObjects> objectsMap;
    private Text viewTimer;
    public Timer gameLoop, stopwatch;
    private Picture[] healthBar = new Picture[3];
    private Picture[] fullHeartPics;
    private Picture[] emptyHeartPics;
    private int currObjCount = 0;
    private int currTime = 0;
    private int elapsedTime = 0;

    public Game() {
        background = new Background();
        this.player = new Player(background);
        objectsMap = new HashMap<>();
        KeyboardLogic keyboardLogic = new KeyboardLogic();
        keyboardLogic.init();
        keyboardLogic.setPlayer(player);
        gameLoop();
        start();
        initHealthPics();
        showHealth();
        SoundManager.getSound("background2").setLoop(20);
    }
    public void init() {

    }
    public void start() {
        stopwatch = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime++;
                int milliseconds = elapsedTime % 1000;
                int seconds = elapsedTime / 1000 % 60;
                int minutes = (elapsedTime / 60000) % 60;
                int hours = (elapsedTime / 36000000) % 24;
                viewTimer.setText("" + String.format("%02d : %02d : %03d", minutes, seconds, milliseconds));
                if (hours == 1) viewTimer.setText("An hour??? Come on... We all now you're using bots!");
                if (player.getHealth() == 0) {
                    stopwatch.stop();
                    Text finalTime = new Text(350, 600, "You Died");
                    finalTime.setText("" + String.format("%02d : %02d : %03d", minutes, seconds, milliseconds));
                    finalTime.grow(100, 70);
                    finalTime.draw();
                }
            }
        });
        viewTimer = new Text(630, 25, "");
        viewTimer.setColor(Color.BLACK);
        viewTimer.draw();
        viewTimer.grow(40, 10);
        stopwatch.start();
        gameLoop.start();
    }

    public void gameLoop() {
        gameLoop = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currTime += 200;
                if (currTime % 1000 == 0) {
                    double rand = Math.random();
                    if (rand < 0.8) {
                        objectsMap.put(currObjCount, new Virus(currObjCount));
                        objectsMap.get(currObjCount);
                        currObjCount++;
                        SoundManager.playSound("blip nasty");
                    } else if (rand >= 0.8 && rand < 0.9) {
                        objectsMap.put(currObjCount, new Vaccine(currObjCount));
                        objectsMap.get(currObjCount);
                        currObjCount++;
                        SoundManager.playSound("vaccine");
                    } else {
                        objectsMap.put(currObjCount, new Mask(currObjCount));
                        objectsMap.get(currObjCount);
                        currObjCount++;
                        SoundManager.playSound("maskAppear");
                    }
                }
                if (player.getImmunityCounter() == 0) {
                    player.setImmunity(false);
                    for (GameObjects object : objectsMap.values()) {
                        if (object instanceof Virus) {
                            ((Virus) object).move(((Virus) object).chooseDirection());
                        }
                        if (player.intersects(object.getHitBox())) {
                            player.setColliding(true);
                            if (object instanceof Virus) {
                                SoundManager.playSound("collision");
                                ((Virus) object).damage(player);initHealthPics();
                                showHealth();
                                System.out.println("damaged" + player.getHealth());
                            } else if (object instanceof Mask) {
                                SoundManager.playSound("bling");
                                if (player.getHealth() == 2) {
                                    healthBar[2] = new Picture(75, 13, "fullHeart.png");
                                    healthBar[2].draw();
                                }
                                if (player.getHealth() == 1) {
                                    healthBar[1] = new Picture(45, 13, "fullHeart.png");
                                    healthBar[1].draw();
                                }
                                ((Mask) object).heal(player);
                                System.out.println("healed" + player.getHealth());
                            } else if (object instanceof Vaccine) {
                                SoundManager.playSound("vaccineEffect");
                                ((Vaccine) object).immunity(player);
                                player.setImmunityCounter(7);
                            }
                        }
                    }
                } else if (player.isImmune()) {
                    for (GameObjects object : objectsMap.values()) {
                        if (object instanceof Virus) {
                            ((Virus) object).move(((Virus) object).chooseDirection());
                            if (player.intersects(object.getHitBox())) {
                                ((Virus) object).delete();
                            }
                        }
                    }
                    if (currTime % 1000 == 0) {
                        player.decreaseImmunityCounter();
                    }
                }
                if (player.getHealth() == 0) {
                    gameLoop.stop();
                    Text gameover = new Text(350, 350, "GAME OVER!");
                    gameover.setColor(Color.RED);
                    gameover.grow(200, 200);
                    gameover.draw();
                    SoundManager.getSound("background").stop();
                }
            }
        });
    }
    private void initHealthPics() {
        fullHeartPics = new Picture[3];
        emptyHeartPics = new Picture[3];
        for (int i = 0; i < fullHeartPics.length; i++) {
            fullHeartPics[i] = new Picture(15 + (i * 30), 13, "fullHeart.png");
            emptyHeartPics[i] = new Picture(15 + (i * 30), 13, "emptyHeart.png");
            healthBar[i] = fullHeartPics[i];
        }
    }

    public void showHealth() {
        if (player.getHealth() == 3) {
            displayHealth(3);
        } else if (player.getHealth() == 2) {
            displayHealth(2);
        } else if (player.getHealth() == 1) {
            displayHealth(1);
        }
    }
    private void displayHealth(int health) {
        for (int i = 0; i < healthBar.length; i++) {
            if (healthBar[i] != null) {
                healthBar[i].delete();
            }
        }
        switch (health) {
            case 1:
                healthBar[0] = fullHeartPics[0];
                healthBar[1] = emptyHeartPics[1];
                healthBar[2] = emptyHeartPics[2];
                break;
            case 2:
                healthBar[0] = fullHeartPics[0];
                healthBar[1] = fullHeartPics[1];
                healthBar[2] = emptyHeartPics[2];
                break;
            case 3:
                healthBar[0] = fullHeartPics[0];
                healthBar[1] = fullHeartPics[1];
                healthBar[2] = fullHeartPics[2];
                break;
            default:
                healthBar[0] = emptyHeartPics[0];
                healthBar[1] = emptyHeartPics[1];
                healthBar[2] = emptyHeartPics[2];
                break;
        }
        for (int i = 0; i < healthBar.length; i++) {
            healthBar[i].draw();
        }
    }
}