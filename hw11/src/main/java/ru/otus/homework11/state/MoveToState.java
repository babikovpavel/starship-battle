package ru.otus.homework11.state;

import ru.otus.homework11.commands.Command;
import ru.otus.homework11.ioc.IoC;

import java.util.Queue;

public class MoveToState implements State {

    @Override
    public State handle(Queue<Command> commands) {
        IoC.resolve("SaveCommands", commands);
        return null;
    }
}
