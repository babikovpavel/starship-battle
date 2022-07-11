package ru.otus.homework8.endpoints;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework8.commands.Command;
import ru.otus.homework8.commands.InterpretCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@ExtendWith(MockitoExtension.class)
class EndpointTest {

    private Map<Long, BlockingQueue<Command>> games;

    @BeforeEach
    public void init() {
        games = new HashMap<>();
        games.put(1L, new LinkedBlockingQueue<>());
        games.put(2L, new LinkedBlockingQueue<>());
    }

    @Test
    void receiveTest() {
        Endpoint endpoint = new Endpoint(games);
        Message message = Message.builder()
                .gameId(1L)
                .objectId(1L)
                .operationId(1L)
                .args(new Object[]{10}).build();
        endpoint.receive(message);
        Assertions.assertThat(games.get(1L))
                .as("should contain new interpret command")
                .hasSize(1)
                .allMatch(command -> command instanceof InterpretCommand);
    }

    @Test
    void receiveUnknownGameTest() {
        Endpoint endpoint = new Endpoint(games);
        Message message = Message.builder()
                .gameId(3L)
                .objectId(1L)
                .operationId(1L)
                .args(new Object[]{10}).build();
        endpoint.receive(message);
        Assertions.assertThat(games.get(1L))
                .as("should contain nothing and not throw")
                .isEmpty();
    }

}