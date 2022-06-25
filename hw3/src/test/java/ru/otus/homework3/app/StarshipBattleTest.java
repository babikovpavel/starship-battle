package ru.otus.homework3.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework3.commands.*;
import ru.otus.homework3.executors.CommandExecutor;
import ru.otus.homework3.executors.CommonCommandExecutor;
import ru.otus.homework3.holders.CsvStrategiesHolder;
import ru.otus.homework3.holders.StrategiesHolder;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class StarshipBattleTest {

    @Spy
    private CommandExecutor commandExecutor = new CommonCommandExecutor();

    @Test
    @DisplayName("should repeat command and log it if fail again")
    void repeatThanLogStrategyTest() throws IOException {
        CommandQueue commandQueue = new CommandQueueImpl();
        StrategiesHolder strategiesHolder = new CsvStrategiesHolder("repeatThanLog.csv");
        commandQueue.append(new SampleCommand());
        StarshipBattle starshipBattle = new StarshipBattle(commandQueue,
                commandExecutor, strategiesHolder);
        starshipBattle.act();
        Assertions.assertAll(() -> {
            Mockito.verify(commandExecutor, times(1)).execute(any(SampleCommand.class));
            Mockito.verify(commandExecutor, times(1)).execute(any(Repeat.class));
            Mockito.verify(commandExecutor, times(1)).execute(any(Log.class));
        });
    }

    @Test
    @DisplayName("should repeat command twice and log it if fail again")
    void repeatTwiceThanLogStrategyTest() throws IOException {
        CommandQueue commandQueue = new CommandQueueImpl();
        StrategiesHolder strategiesHolder = new CsvStrategiesHolder("repeatTwiceThanLog.csv");
        commandQueue.append(new SampleCommand());
        StarshipBattle starshipBattle = new StarshipBattle(commandQueue,
                commandExecutor, strategiesHolder);
        starshipBattle.act();
        Assertions.assertAll(() -> {
            Mockito.verify(commandExecutor, times(1)).execute(any(SampleCommand.class));
            Mockito.verify(commandExecutor, times(2)).execute(any(Repeat.class));
            Mockito.verify(commandExecutor, times(1)).execute(any(Log.class));
        });
    }
}