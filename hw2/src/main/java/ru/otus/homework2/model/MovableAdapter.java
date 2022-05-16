package ru.otus.homework2.model;

import java.util.Vector;

public class MovableAdapter implements Movable {

    private final UObject object;

    public MovableAdapter(UObject object) {
        this.object = object;
    }

    @Override
    public Vector<Integer> getPosition() {
        return (Vector<Integer>) object.getProperty("position");
    }

    @Override
    public void setPosition(Vector<Integer> newPosition) {
        object.setProperty("position", newPosition);
    }

    @Override
    public Vector<Double> getVelocity() {
        Vector<Double> actualVelocity = new Vector<>();
        int direction = (int) object.getProperty("direction");
        int directionsNumber = (int) object.getProperty("directionsNumber");
        int velocity = (int) object.getProperty("velocity");
        actualVelocity.add(velocity * Math.cos((double) direction / 360 * directionsNumber));
        actualVelocity.add(velocity * Math.sin((double) direction / 360 * directionsNumber));
        return actualVelocity;
    }
}
