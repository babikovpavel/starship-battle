package ru.otus.homework3.commands;

import java.util.Deque;
import java.util.LinkedList;

public class CommandQueueImpl implements CommandQueue {

    private final Deque<Command> commands;

    public CommandQueueImpl() {
        this.commands = new LinkedList<>();
    }

    @Override
    public void prepend(Command command) {
        commands.addFirst(command);
    }

    @Override
    public void append(Command command) {
        commands.addLast(command);
    }

    @Override
    public Command getNext() {
        return hasNext() ? commands.poll() : null;
    }

    @Override
    public boolean hasNext() {
        return !commands.isEmpty();
    }
}
