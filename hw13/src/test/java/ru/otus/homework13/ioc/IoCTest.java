package ru.otus.homework13.ioc;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.otus.homework13.commands.StartMoveCommand;
import ru.otus.homework13.model.UObject;

import static org.junit.jupiter.api.Assertions.assertAll;

class IoCTest {

    @Mock
    private UObject uObject;

    @Test
    @DisplayName("should return command from interpretator")
    void resolveCommandByInterpretator() {
        String source = "{\"id\": \"548\", \"action\": \"StartMove\", \"initialVelocity\": \"2\"}";
        var command = IoC.resolve("Interpret", source);
        assertAll(() -> {
            Assertions.assertThat(command)
                    .isNotNull()
                    .isInstanceOf(StartMoveCommand.class);
        });
    }
}