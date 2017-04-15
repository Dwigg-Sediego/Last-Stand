package com.dwigg.laststand.entities.components;

import com.badlogic.ashley.core.Component;

public class DirectionComponent implements Component {

    public enum Direction {
        LEFT, RIGHT
    }

    private Direction direction = Direction.RIGHT;

    public void left() {
        direction = Direction.LEFT;
    }

    public void right() {
        direction = Direction.RIGHT;
    }

    public Direction get() {
        return direction;
    }
}
