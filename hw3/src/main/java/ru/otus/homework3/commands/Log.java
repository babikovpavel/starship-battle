package ru.otus.homework3.commands;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Log implements Command {

    private final Command command;
    private final Exception exception;

    public Log(Command command, Exception exception) {
        this.command = command;
        this.exception = exception;
    }

    @Override
    public void execute() {
        log.error("Error of type {} occurred with message {} when execute {}",
                exception.getClass(), exception.getMessage(), command.toString());
    }

    @Override
    public String getName() {
        return "Log";
    }
}
