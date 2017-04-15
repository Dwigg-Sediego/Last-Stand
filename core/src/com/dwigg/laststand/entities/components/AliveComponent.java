package com.dwigg.laststand.entities.components;

import com.badlogic.ashley.core.Component;

public class AliveComponent implements Component {

    private boolean isAlive = true;

    public void set(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean get() {
        return isAlive;
    }
}
