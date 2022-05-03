package ru.otus.homework2.model;

public interface Rotatable {

    Direction getDirection();

    int getAngularVelocity();

    void setDirection(Direction newDirection);

}
