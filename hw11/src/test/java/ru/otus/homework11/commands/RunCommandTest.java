package ru.otus.homework11.commands;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework11.state.NormalState;
import ru.otus.homework11.utils.TestUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class RunCommandTest {

    @Mock
    private Command simpleCommand;

    @Test
    @DisplayName("should start game in a new thread")
    void startNewGameTest() {
        Queue<Command> commands = new LinkedList<>();
        RunCommand runCommand = new RunCommand(commands, new NormalState());
        runCommand.execute();
        assertThat(TestUtils.getCurrentThreads())
                .as("current threads should contains game thread")
                .anyMatch(thread -> thread.getName().equals("GAME"));
        assertThat(runCommand.getGameState()).isInstanceOf(NormalState.class);
    }

    @Test
    @DisplayName("thread should continue if simple command throws an error")
    void startNewGameExceptionTest() {
        Queue<Command> commands = new LinkedList<>(List.of(simpleCommand));
        RunCommand runCommand = new RunCommand(commands, new NormalState());
        Mockito.doThrow(IllegalArgumentException.class)
                .when(simpleCommand).execute();
        runCommand.execute();
        Awaitility.await()
                .untilAsserted(() -> Mockito.verify(simpleCommand, times(1)).execute());
        assertThat(TestUtils.getCurrentThreads())
                .as("current threads should contains game thread")
                .anyMatch(thread -> thread.getName().equals("GAME"));
    }
}