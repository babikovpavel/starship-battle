package ru.otus.homework11.commands;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework11.state.State;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class HardStopCommandTest {

    @Mock
    private Command simpleCommand;

    @Spy
    private State state;

    @Test
    @DisplayName("should stop game thread and not run simple command")
    void hardStopTest() throws InterruptedException {
        //arrange
        Queue<Command> commands = new LinkedList<>();
        commands.addAll(List.of(new HardStopCommand(state), simpleCommand));
        //act
        RunCommand command = new RunCommand(commands, state);
        command.execute();
        //assert
        Thread.sleep(1000L);
        Mockito.verify(simpleCommand, times(0)).execute();
        Assertions.assertThat(command.getGameState()).isNull();
    }
}