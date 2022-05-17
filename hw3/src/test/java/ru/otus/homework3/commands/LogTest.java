package ru.otus.homework3.commands;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class LogTest {

    @Test
    @DisplayName("should log command data and thrown exception")
    void logFailedCommandTest() {
        LogCaptor logCaptor = LogCaptor.forClass(Log.class);
        Command command = Mockito.mock(Command.class);
        Exception thrownException = new Exception("test");
        Log logCommand = new Log(command, thrownException);
        logCommand.execute();
        assertThat(logCaptor.getLogs())
                .as("Logs should contains messages")
                .isNotEmpty();
    }
}