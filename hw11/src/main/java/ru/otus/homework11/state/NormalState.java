package ru.otus.homework11.state;

import ru.otus.homework11.commands.Command;
import ru.otus.homework11.handlers.ExceptionHandler;
import ru.otus.homework11.ioc.IoC;

import java.util.Objects;
import java.util.Queue;

public class NormalState implements State {

    @Override
    public State handle(Queue<Command> commands) {
        Command nextCommand = null;
        try {
            nextCommand = commands.poll();
            if (nextCommand != null)
                Objects.requireNonNull(nextCommand).execute();
        } catch (Exception exception) {
            ((ExceptionHandler) IoC.resolve("ExceptionHandler"))
                    .handle(nextCommand, exception);
        }
        return this;
    }
}
