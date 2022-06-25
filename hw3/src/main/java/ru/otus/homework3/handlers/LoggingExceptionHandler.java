package ru.otus.homework3.handlers;

import ru.otus.homework3.commands.Command;
import ru.otus.homework3.commands.CommandQueue;
import ru.otus.homework3.commands.Log;

public class LoggingExceptionHandler extends AbstractExceptionHandler {

    public LoggingExceptionHandler(CommandQueue commandQueue) {
        super(commandQueue);
    }

    @Override
    public void handle(Command command, Exception exception) {
        commandQueue.prepend(new Log(command, exception));
    }
}
