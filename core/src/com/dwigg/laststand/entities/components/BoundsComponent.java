package com.dwigg.laststand.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

public class BoundsComponent implements Component {

    public float offsetX = 0;
    public Rectangle rectangle;

    public BoundsComponent(float width, float height, float offsetX) {
        this.offsetX = offsetX;
        rectangle = new Rectangle(0, 0, width, height);
    }
}
