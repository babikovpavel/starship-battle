package ru.otus.homework4.commands;

public interface CommandsQueue {

    void append(Command command);

    void prepend(Command command);

    Boolean hasNext();

    Command getNext();
}
