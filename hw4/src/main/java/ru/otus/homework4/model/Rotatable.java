package ru.otus.homework4.model;

import ru.otus.homework4.model.Direction;

public interface Rotatable {

    Direction getDirection();

    int getAngularVelocity();

    void setDirection(Direction newDirection);

}
