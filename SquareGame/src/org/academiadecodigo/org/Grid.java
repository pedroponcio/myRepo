package org.academiadecodigo.org;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Grid {

    public static final int PADDING = 10;
    public static final int CELLSIZE = 30;
    private int cols;
    private int rows;
    private Rectangle field;

    private Rectangle[][] position;


    public Grid (int cols, int rows){
       this.cols = cols;
       this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public int getMaxX(){
        return cols*CELLSIZE;
    }

    public int getMaxY(){
        return rows*CELLSIZE;
    }

    public void init() {
        this.field = new Rectangle(PADDING,PADDING, cols*CELLSIZE, rows*CELLSIZE);
        this.field.draw();

        position = new Rectangle[cols][rows];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {

                position[i][j] = new Rectangle(PADDING + (CELLSIZE * i), PADDING + (CELLSIZE * j), CELLSIZE, CELLSIZE);
                position[i][j].draw();
            }
        }
    }

    public void stringToGrid(String grid){

           int index = 0;
            System.out.println(grid);
        for (int i = 0; i < rows; i++) {
            System.out.println("column!" + i);
            for (int j = 0; j < cols; j++) {

                Rectangle positionToLoad = position[j][i];

                System.out.println(grid.charAt(index));

                if (grid.charAt(index) == '0') {
                    positionToLoad.draw();
                } else {
                    positionToLoad.fill();
                }
               index++;
            }
            index++;
        }
    }

    public Rectangle [][] gridRectangle() {
        return position;
    }
    
    public int gridRectangleLength(){
        return position.length;
    }

/*    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                stringBuilder.append(gridRectangle()[i][j]);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }*/
}
