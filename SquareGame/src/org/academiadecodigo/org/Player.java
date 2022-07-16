package org.academiadecodigo.org;


import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;

public class Player {

    private Rectangle rectanglePlayer;

    private Rectangle[][] playerPosition;
    private Rectangle selectedRectangle;

    private Rectangle clearRectangle;
    private Grid grid;

    private Keyboard keyboard;
    private int initialCol = Grid.PADDING;
    private int initalRow = Grid.PADDING;

    public Player(Grid grid) {
        this.grid = grid;
        rectanglePlayer = new Rectangle(initialCol, initalRow, Grid.CELLSIZE, Grid.CELLSIZE);
        rectanglePlayer.setColor(Color.GREEN);
        rectanglePlayer.fill();
    }

    public void moveLeft() {
        if (rectanglePlayer.getX() > Grid.PADDING + 1) {
            rectanglePlayer.translate(-Grid.CELLSIZE, 0);
        }
    }

    public void moveRight() {
        if (rectanglePlayer.getX() < grid.getMaxX() - Grid.CELLSIZE) {
            rectanglePlayer.translate(Grid.CELLSIZE, 0);
        }
    }

    public void moveUp() {
        if (rectanglePlayer.getY() > Grid.PADDING + 1) {
            rectanglePlayer.translate(0, -Grid.CELLSIZE);
        }
    }

    public void moveDown() {
        if (rectanglePlayer.getY() < grid.getMaxY() - Grid.CELLSIZE) {
            rectanglePlayer.translate(0, Grid.CELLSIZE);
        }
    }

    public void selectSquare() {
        if (!grid.gridRectangle()[(rectanglePlayer.getX()) / Grid.CELLSIZE][(rectanglePlayer.getY()) / Grid.CELLSIZE].isFilled()) {
            grid.gridRectangle()[(rectanglePlayer.getX()) / Grid.CELLSIZE][(rectanglePlayer.getY()) / Grid.CELLSIZE].setColor(Color.BLACK);
            grid.gridRectangle()[(rectanglePlayer.getX()) / Grid.CELLSIZE][(rectanglePlayer.getY()) / Grid.CELLSIZE].fill();
        } else {
            grid.gridRectangle()[(rectanglePlayer.getX()) / Grid.CELLSIZE][(rectanglePlayer.getY()) / Grid.CELLSIZE].draw();
        }
    }

    public void clear() {
        for (int i = 0; i < grid.getCols(); i++) {
            for (int j = 0; j < grid.getRows(); j++) {
                if (grid.gridRectangle()[i][j].isFilled()) {
                    grid.gridRectangle()[i][j].draw();
                }
            }
        }
    }

    public void load() {
        grid.stringToGrid(FileManager.readFile());
    }

    @Override
    public String toString() {

        String value = "";

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getCols(); j++) {
                System.out.println("rows: "+grid.getRows());
                System.out.println("cols: "+grid.getCols());
                if (grid.gridRectangle()[j][i].isFilled()) {
                    value = value + "1";
                } else {
                    value = value + "0";
                }
            }
            value = value + "\n";
        }
        return value;
    }

    public void save() {
        FileManager.writeToFile(toString());
    }
}
