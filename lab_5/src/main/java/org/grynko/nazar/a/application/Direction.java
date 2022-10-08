package org.grynko.nazar.a.application;

public enum Direction {

    RIGHT,
    LEFT,
    ;

    public static Direction unfold(Direction direction) {
        return direction == RIGHT ? LEFT : RIGHT;
    }

    @Override
    public String toString() {
        return this == RIGHT ? "R" : "L";
    }
}
