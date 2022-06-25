package ru.otus.homework3.app;

import ru.otus.homework3.holders.StrategiesHolder;
import ru.otus.homework3.commands.Command;
import ru.otus.homework3.executors.CommandExecutor;
import ru.otus.homework3.commands.CommandQueue;
import ru.otus.homework3.handlers.ExceptionHandler;
import ru.otus.homework3.handlers.GenericExceptionHandler;

public class StarshipBattle {

    private final CommandQueue commandQueue;
    private final CommandExecutor commandExecutor;
    private final ExceptionHandler exceptionHandler;

    public StarshipBattle(CommandQueue commandQueue,
                          CommandExecutor commandExecutor,
                          StrategiesHolder strategiesHolder) {
        this.commandQueue = commandQueue;
        this.commandExecutor = commandExecutor;
        this.exceptionHandler = new GenericExceptionHandler(commandQueue, strategiesHolder);
    }

    public void act() {
        while (commandQueue.hasNext()) {
            Command command = commandQueue.getNext();
            try {
                commandExecutor.execute(command);
            } catch (Exception exception) {
                exceptionHandler.handle(command, exception);
            }
        }
    }
}
