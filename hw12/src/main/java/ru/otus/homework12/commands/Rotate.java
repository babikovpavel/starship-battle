package ru.otus.homework12.commands;

import ru.otus.homework12.model.Rotatable;

public class Rotate implements Command {

    private final Rotatable rotatable;

    public Rotate(Rotatable rotatable) {
        this.rotatable = rotatable;
    }

    @Override
    public void execute() {
        rotatable.getDirection().next(rotatable.getAngularVelocity());
    }
}
