package ru.otus.homework7.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SoftStopCommandTest {

    @Mock
    private Command simpleCommand;

    @Test
    @DisplayName("should stop game thread after simple command run")
    void softStopTest() throws InterruptedException {
        //arrange
        Queue<Command> commandsQueue = new LinkedList<>();
        ThreadLocal<Queue<Command>> commands = ThreadLocal.withInitial(() -> commandsQueue);
        ThreadLocal<Boolean> continueCondition = ThreadLocal.withInitial(() -> Boolean.TRUE);
        commandsQueue.addAll(List.of(new SoftStopCommand(commands, continueCondition), simpleCommand));
        StartQueueCommand startQueueCommand = new StartQueueCommand(commands, continueCondition);
        //act
        startQueueCommand.execute();
        //assert
        Thread.sleep(1000L);
        Mockito.verify(simpleCommand, times(1)).execute();
    }
}