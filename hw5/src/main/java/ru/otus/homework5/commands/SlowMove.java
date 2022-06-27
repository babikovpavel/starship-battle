package ru.otus.homework5.commands;

import ru.otus.homework5.model.Movable;

public class SlowMove extends Move {

    public SlowMove(Movable movable) {
        super(movable);
    }

    @Override
    public void execute() {
        var position = movable.getPosition();
        position.set(0, position.get(0) + 1);
        position.set(1, position.get(0) + 1);
        this.movable.setPosition(position);
    }
}
