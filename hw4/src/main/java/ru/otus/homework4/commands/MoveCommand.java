package ru.otus.homework4.commands;

import ru.otus.homework4.model.Movable;

import java.util.Vector;

public class MoveCommand implements Command {

    private final Movable movable;

    public MoveCommand(Movable movable) {
        this.movable = movable;
    }

    @Override
    public void execute() {
        Vector<Integer> currentPosition = movable.getPosition();
        Vector<Double> actualVelocity = movable.getVelocity();
        Vector<Integer> newPosition = new Vector<>();
        for (int i = 0; i < currentPosition.size(); i++) {
            newPosition.add((int) (currentPosition.get(i) + actualVelocity.get(i)));
        }
        movable.setPosition(newPosition);
    }
}
