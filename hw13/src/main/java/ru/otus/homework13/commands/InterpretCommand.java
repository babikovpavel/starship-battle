package ru.otus.homework13.commands;

import lombok.RequiredArgsConstructor;
import ru.otus.homework13.ioc.IoC;

@RequiredArgsConstructor
public class InterpretCommand implements Command {

    private final String commandSource;

    @Override
    public void execute() {
        Command command = (Command) IoC.resolve("Interpret", commandSource);
        command.execute();
    }
}
