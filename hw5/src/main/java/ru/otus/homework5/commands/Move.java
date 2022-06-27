package ru.otus.homework5.commands;

import ru.otus.homework5.model.Movable;

public abstract class Move implements Command {

    protected final Movable movable;

    Move(Movable movable) {
        this.movable = movable;
    }
}
