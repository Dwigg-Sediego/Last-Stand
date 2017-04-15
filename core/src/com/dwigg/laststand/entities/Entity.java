package com.dwigg.laststand.entities;


abstract class Entity extends com.badlogic.ashley.core.Entity {

    abstract void addComponents();

    public abstract void dispose();
}
