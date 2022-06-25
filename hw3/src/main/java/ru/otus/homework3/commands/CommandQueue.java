package ru.otus.homework3.commands;

public interface CommandQueue {

    void prepend(Command command);

    void append(Command command);

    Command getNext();

    boolean hasNext();
}
