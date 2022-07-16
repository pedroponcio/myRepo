package org.academiadecodigo.unbitables;

public class Position{

    private Background background;
    private int col, row;

    public Position (int col, int row) {
        setPos(col, row);
    }

    public void setPos(int col, int row) {
        this.col = col;
        this.row = row;
    }
    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void moveRight(int dist) {
        setPos(getCol() + dist, getRow());
    }
    public void moveLeft(int dist) {
        setPos(getCol() - dist, getRow());
    }
    public void moveUp(int dist) {
        setPos(getCol(), getRow() - dist);
    }
    public void moveDown(int dist) {
        setPos(getCol(), getRow() + dist);}
}
