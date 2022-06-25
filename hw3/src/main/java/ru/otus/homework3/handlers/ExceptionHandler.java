package ru.otus.homework3.handlers;

import ru.otus.homework3.commands.Command;

public interface ExceptionHandler {

    void handle(Command command, Exception exception);
}
