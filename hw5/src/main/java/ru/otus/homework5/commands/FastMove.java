package ru.otus.homework5.commands;

import ru.otus.homework5.model.Movable;

public class FastMove extends Move {

    public FastMove(Movable movable) {
        super(movable);
    }

    @Override
    public void execute() {
        var position = movable.getPosition();
        position.set(0, position.get(0) + 10);
        position.set(1, position.get(0) + 10);
        this.movable.setPosition(position);
    }
}
