package ru.otus.homework4.model;

import ru.otus.homework4.model.Direction;
import ru.otus.homework4.model.Rotatable;

public class RotatableAdapter implements Rotatable {

    private final UObject object;

    public RotatableAdapter(UObject object) {
        this.object = object;
    }

    @Override
    public Direction getDirection() {
        return (Direction) object.getProperty("direction");
    }

    @Override
    public int getAngularVelocity() {
        return (int) object.getProperty("angularVelocity");
    }

    @Override
    public void setDirection(Direction newDirection) {
        object.setProperty("direction", newDirection);
    }
}
