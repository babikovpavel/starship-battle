package ru.otus.homework12.model;

import java.util.Vector;

public interface AreaMoveable {

    Vector<Integer> getPosition();

    void setPosition(Vector<Integer> newPosition);

    Vector<Double> getVelocity();

    Area getArea();

    void setArea(Area area);
}