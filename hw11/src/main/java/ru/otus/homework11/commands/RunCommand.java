package ru.otus.homework11.commands;

import lombok.Getter;
import ru.otus.homework11.state.State;

import java.util.Queue;

@Getter
public class RunCommand implements Command {

    private final Queue<Command> commands;

    private State gameState;
    private Thread gameThread;

    public RunCommand(Queue<Command> commands,
                      State gameState) {
        this.commands = commands;
        this.gameState = gameState;
    }

    @Override
    public void execute() {
        Runnable runnable = () -> {
            while (gameState != null) {
                gameState = gameState.handle(commands);
            }
            gameThread.interrupt();
        };
        gameThread = new Thread(runnable, "GAME");
        gameThread.start();
    }
}
