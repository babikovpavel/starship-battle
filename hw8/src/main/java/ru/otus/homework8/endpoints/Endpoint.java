package ru.otus.homework8.endpoints;

import lombok.RequiredArgsConstructor;
import ru.otus.homework8.commands.Command;
import ru.otus.homework8.commands.InterpretCommand;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

@RequiredArgsConstructor
public class Endpoint {

    private final Map<Long, BlockingQueue<Command>> games;

    void receive(Message message) {
        BlockingQueue<Command> game = games.get(message.getGameId());
        if (game != null) {
            game.add(new InterpretCommand(message.getGameId(),
                    message.getObjectId(),
                    message.getArgs(),
                    game));
        }
    }
}
