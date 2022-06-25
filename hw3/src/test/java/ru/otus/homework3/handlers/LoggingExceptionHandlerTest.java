package ru.otus.homework3.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework3.commands.CommandQueue;
import ru.otus.homework3.commands.Log;
import ru.otus.homework3.commands.SampleCommand;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LoggingExceptionHandlerTest {

    @Mock
    private CommandQueue commandQueue;

    private LoggingExceptionHandler loggingExceptionHandler;

    @BeforeEach
    void setUp() {
        this.loggingExceptionHandler = new LoggingExceptionHandler(commandQueue);
    }

    @Test
    @DisplayName("should add log command of failed command to queue")
    void handleTest() {
        loggingExceptionHandler.handle(new SampleCommand(), new IllegalArgumentException());
        verify(commandQueue).prepend(any(Log.class));
    }
}