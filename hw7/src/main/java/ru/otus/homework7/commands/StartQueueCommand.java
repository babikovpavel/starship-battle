package ru.otus.homework7.commands;

import ru.otus.homework7.handlers.ExceptionHandler;
import ru.otus.homework7.ioc.IoC;

import java.util.Objects;
import java.util.Queue;

public class StartQueueCommand implements Command {

    private final ThreadLocal<Queue<Command>> commandsQueue;
    private final ThreadLocal<Boolean> continueCondition;

    private Thread gameThread;

    public StartQueueCommand(ThreadLocal<Queue<Command>> commandsQueue,
                             ThreadLocal<Boolean> continueCondition) {
        this.commandsQueue = commandsQueue;
        this.continueCondition = continueCondition;
    }

    @Override
    public void execute() {
        Runnable runnable = () -> {
            Command nextCommand = null;
            while (Boolean.TRUE.equals(continueCondition.get())) {
                try {
                    nextCommand = commandsQueue.get().poll();
                    if (nextCommand != null)
                        Objects.requireNonNull(nextCommand).execute();
                } catch (Exception exception) {
                    ((ExceptionHandler) IoC.resolve("ExceptionHandler"))
                            .handle(nextCommand, exception);
                }
            }
            gameThread.interrupt();
        };
        gameThread = new Thread(runnable, "GAME");
        gameThread.start();
    }
}
