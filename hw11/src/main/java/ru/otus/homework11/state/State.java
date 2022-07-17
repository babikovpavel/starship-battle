package ru.otus.homework11.state;

import ru.otus.homework11.commands.Command;

import java.util.Queue;

public interface State {

    State handle(Queue<Command> commands);
}
