package ru.otus.homework13.interpretator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.homework13.commands.Command;
import ru.otus.homework13.commands.ShootCommand;
import ru.otus.homework13.commands.StartMoveCommand;
import ru.otus.homework13.commands.StopMoveCommand;
import ru.otus.homework13.exceptions.UnknownCommandException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandsInterpreterTest {

    private Interpretator<Command> interpretator;

    @BeforeEach
    public void init() {
        interpretator = new CommandsInterpreter();
    }

    @Test
    void getStartMoveCommandTest() {
        String source = "{\"id\": \"548\", \"action\": \"StartMove\", \"initialVelocity\": \"2\"}";
        Command command = interpretator.interpret(source);
        Assertions.assertThat(command)
                .isInstanceOf(StartMoveCommand.class);
    }

    @Test
    void getStopMoveCommandTest() {
        String source = "{\"id\": \"548\", \"action\": \"StopMove\"}";
        Command command = interpretator.interpret(source);
        Assertions.assertThat(command)
                .isInstanceOf(StopMoveCommand.class);
    }

    @Test
    void getShootCommandTest() {
        String source = "{\"id\": \"548\", \"action\": \"Shoot\"}";
        Command command = interpretator.interpret(source);
        Assertions.assertThat(command)
                .isInstanceOf(ShootCommand.class);
    }

    @Test
    void getIncorrectCommandTest() {
        String source = "{\"id\": \"548\", \"action123\": \"StartMove11111\", \"initialVelocity\": \"2\"}";
        assertThrows(UnknownCommandException.class, () -> interpretator.interpret(source));
    }
}