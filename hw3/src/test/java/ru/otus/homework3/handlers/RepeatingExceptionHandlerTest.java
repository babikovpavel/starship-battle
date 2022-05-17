package ru.otus.homework3.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework3.commands.CommandQueue;
import ru.otus.homework3.commands.Repeat;
import ru.otus.homework3.commands.SampleCommand;

import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RepeatingExceptionHandlerTest {

    @Mock
    private CommandQueue commandQueue;

    private RepeatingExceptionHandler repeatingExceptionHandler;

    @BeforeEach
    void setUp() {
        this.repeatingExceptionHandler = new RepeatingExceptionHandler(commandQueue);
    }

    @Test
    @DisplayName("should prepend repeat command of failed command to queue")
    void handleTest() {
        repeatingExceptionHandler.handle(new SampleCommand(), new IllegalArgumentException());
        verify(commandQueue).prepend(argThat(argument -> argument instanceof Repeat &&
                (((Repeat) argument).getCommand() instanceof SampleCommand)));
    }
}