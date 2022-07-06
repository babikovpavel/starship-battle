package ru.otus.homework7.handlers;

import ru.otus.homework7.commands.Command;

public interface ExceptionHandler {

    void handle(Command command, Exception exception);
}
