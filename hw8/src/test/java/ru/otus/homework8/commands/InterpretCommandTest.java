package ru.otus.homework8.commands;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework8.ioc.IoC;
import ru.otus.homework8.model.SampleObject;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class InterpretCommandTest {

    private BlockingQueue<Command> gameCommands;

    @BeforeEach
    public void init() {
        gameCommands = new LinkedBlockingQueue<>();
    }

    @Test
    void executeTest() {
        try (MockedStatic<IoC> utilities = Mockito.mockStatic(IoC.class)) {
            utilities.when(() -> IoC.resolve("GameObject", 1L))
                    .thenReturn(new SampleObject());
            utilities.when(() -> IoC.resolve(eq("Operation"), any()))
                    .thenReturn(new SampleCommand(10));
            InterpretCommand interpretCommand = new InterpretCommand(1L, 1L, new Object[]{10}, gameCommands);
            interpretCommand.execute();
            Assertions.assertThat(gameCommands)
                    .as("game queue should contain new sample command with value of 10")
                    .hasSize(1)
                    .allMatch(command -> command instanceof SampleCommand
                            && ((SampleCommand) command).getCount() == 10);
        }
    }
}