package ru.otus.homework7.commands;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework7.ioc.IoC;
import ru.otus.homework7.utils.TestUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class StartQueueCommandTest {

    @Mock
    private Command simpleCommand;

    private Queue<Command> commands;

    @Test
    @DisplayName("should start game in a new thread")
    void startNewGameTest() {
        Queue<Command> commands = new LinkedList<>();
        ThreadLocal<Boolean> continueCondition = ThreadLocal.withInitial(() -> Boolean.TRUE);
        StartQueueCommand startQueueCommand = new StartQueueCommand(ThreadLocal.withInitial(() -> commands),
                continueCondition);
        startQueueCommand.execute();
        assertThat(TestUtils.getCurrentThreads())
                .as("current threads should contains game thread")
                .anyMatch(thread -> thread.getName().equals("GAME"));
    }

    @Test
    @DisplayName("thread should continue if simple command throws an error")
    void startNewGameExceptionTest() {
        Queue<Command> commands = new LinkedList<>(List.of(simpleCommand));
        ThreadLocal<Boolean> continueCondition = ThreadLocal.withInitial(() -> Boolean.TRUE);
        StartQueueCommand startQueueCommand = new StartQueueCommand(ThreadLocal.withInitial(() -> commands),
                continueCondition);
        Mockito.doThrow(IllegalArgumentException.class)
                .when(simpleCommand).execute();
        startQueueCommand.execute();
        Awaitility.await()
                .untilAsserted(() -> Mockito.verify(simpleCommand, times(1)).execute());
        assertThat(TestUtils.getCurrentThreads())
                .as("current threads should contains game thread")
                .anyMatch(thread -> thread.getName().equals("GAME"));
    }
}