package ru.otus.homework4.commands;

import ru.otus.homework4.model.Rotatable;

public class RotateCommand implements Command {

    private final Rotatable rotatable;

    public RotateCommand(Rotatable rotatable) {
        this.rotatable = rotatable;
    }

    @Override
    public void execute() {
        rotatable.getDirection().next(rotatable.getAngularVelocity());
    }
}
