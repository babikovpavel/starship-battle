package ru.otus.homework8.commands;

import lombok.RequiredArgsConstructor;
import ru.otus.homework8.ioc.IoC;
import ru.otus.homework8.model.UObject;

import java.util.concurrent.BlockingQueue;

@RequiredArgsConstructor
public class InterpretCommand implements Command {

    private final long objectId;
    private final long operationId;
    private final Object[] args;
    private final BlockingQueue<Command> gameCommands;

    @Override
    public void execute() {
        UObject gameObject = (UObject) IoC.resolve("GameObject", objectId);
        Command command = (Command) IoC.resolve("Operation", operationId, gameObject, args);
        gameCommands.add(command);
    }
}