package ru.otus.homework3.handlers;

import lombok.SneakyThrows;
import ru.otus.homework3.commands.Command;
import ru.otus.homework3.commands.CommandQueue;
import ru.otus.homework3.exceptions.NoSuchHandlerException;
import ru.otus.homework3.holders.StrategiesHolder;

import java.util.Objects;

public class GenericExceptionHandler extends AbstractExceptionHandler {

    private final StrategiesHolder strategiesHolder;

    public GenericExceptionHandler(CommandQueue commandQueue,
                                   StrategiesHolder strategiesHolder) {
        super(commandQueue);
        this.strategiesHolder = strategiesHolder;
    }

    @Override
    @SneakyThrows
    public void handle(Command command, Exception exception) {
        String strategyHash = getHash(command, exception);
        String handlerClass = strategiesHolder.getHandlerClassByHash(strategyHash);
        if (handlerClass == null) {
            throw new NoSuchHandlerException(
                    String.format("No such handler for command %s and exception %s with hash %s",
                            command.getName(), exception, strategyHash));
        }
        AbstractExceptionHandler exceptionHandler =
                (AbstractExceptionHandler) Class.forName(handlerClass)
                        .getConstructor(CommandQueue.class).newInstance(commandQueue);
        exceptionHandler.handle(command, exception);
    }

    private static String getHash(Command command, Exception exception) {
        return String.valueOf(Objects.hash(command.getName(), exception.toString()));
    }
}
