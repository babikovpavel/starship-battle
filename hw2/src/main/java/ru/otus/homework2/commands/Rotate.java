package ru.otus.homework2.commands;

import ru.otus.homework2.model.Rotatable;

public class Rotate {

    private final Rotatable rotatable;

    public Rotate(Rotatable rotatable) {
        this.rotatable = rotatable;
    }

    public void execute() {
        rotatable.getDirection().next(rotatable.getAngularVelocity());
    }
}
