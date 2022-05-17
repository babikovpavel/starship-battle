package ru.otus.homework3.handlers;

import ru.otus.homework3.commands.CommandQueue;

public abstract class AbstractExceptionHandler implements ExceptionHandler {

    protected CommandQueue commandQueue;

    AbstractExceptionHandler(CommandQueue commandQueue) {
        this.commandQueue = commandQueue;
    }
}
