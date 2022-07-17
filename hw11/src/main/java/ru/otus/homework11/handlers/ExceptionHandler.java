package ru.otus.homework11.handlers;

import ru.otus.homework11.commands.Command;

public interface ExceptionHandler {

    void handle(Command command, Exception exception);
}
