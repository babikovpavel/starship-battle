package ru.otus.homework3.handlers;

import ru.otus.homework3.commands.Command;
import ru.otus.homework3.commands.CommandQueue;
import ru.otus.homework3.commands.FailRepeat;
import ru.otus.homework3.commands.Repeat;

public class RepeatingExceptionHandler extends AbstractExceptionHandler {

    public RepeatingExceptionHandler(CommandQueue commandQueue) {
        super(commandQueue);
    }

    @Override
    public void handle(Command command, Exception exception) {
        if(command instanceof Repeat) {
            commandQueue.prepend(new FailRepeat(command));
        } else {
            commandQueue.prepend(new Repeat(command));
        }
    }
}
