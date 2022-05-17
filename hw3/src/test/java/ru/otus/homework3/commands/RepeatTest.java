package ru.otus.homework3.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

class RepeatTest {

    @Test
    @DisplayName("should execute command at input")
    void repeatCommandTest() {
        Command command = Mockito.mock(SampleCommand.class);
        Repeat repeat = new Repeat(command);
        repeat.execute();
        Mockito.verify(command, times(1)).execute();
    }
}