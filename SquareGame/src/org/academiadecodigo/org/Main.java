package org.academiadecodigo.org;

public class Main {

    public static void main(String[] args) {

        Grid gridTest = new Grid(20, 20);
        Player player = new Player(gridTest);
        Controls controls = new Controls(player);
        gridTest.init();

    }

}
