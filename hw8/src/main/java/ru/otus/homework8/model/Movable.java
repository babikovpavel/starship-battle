package ru.otus.homework8.model;

import java.util.Vector;

public interface Movable {

    Vector<Integer> getPosition();

    void setPosition(Vector<Integer> newPosition);

    Vector<Double> getVelocity();

    void finish();
}