package ru.otus.homework12.model;

public interface Rotatable {

    Direction getDirection();

    int getAngularVelocity();

    void setDirection(Direction newDirection);

}
