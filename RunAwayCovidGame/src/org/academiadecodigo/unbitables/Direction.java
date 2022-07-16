package org.academiadecodigo.unbitables;

public enum Direction {

    UP,
    DOWN,
    LEFT,
    RIGHT;

    public boolean isOpposite(Direction dir) {
        return dir.equals(oppositeDirection());
    }

    /**
     * Obtains the opposite direction
     *
     * @return the opposite direction
     */
    public Direction oppositeDirection() {

        Direction opposite = null;

        switch (this) {
            case UP:
                opposite = Direction.DOWN;
                break;
            case DOWN:
                opposite = Direction.UP;
                break;
            case LEFT:
                opposite = Direction.RIGHT;
                break;
            case RIGHT:
                opposite = Direction.LEFT;
                break;
        }

        return opposite;
    }

}
