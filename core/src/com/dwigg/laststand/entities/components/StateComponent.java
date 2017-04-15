package com.dwigg.laststand.entities.components;

import com.badlogic.ashley.core.Component;

public class StateComponent implements Component {

    private String state = "IDLE";
    public float time = 0.0f;

    public void set(String state) {
        this.state = state;
        time = 0.0f;
    }

    public String get() {
        return state;
    }
}
