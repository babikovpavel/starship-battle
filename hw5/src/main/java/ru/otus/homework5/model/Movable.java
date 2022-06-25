package ru.otus.homework5.model;

import java.util.Vector;

public interface Movable {

    Vector<Integer> getPosition();

    void setPosition(Vector<Integer> newPosition);

    Vector<Double> getVelocity();
}