package ru.otus.homework11.commands;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework11.state.MoveToState;
import ru.otus.homework11.state.NormalState;
import ru.otus.homework11.state.State;

@ExtendWith(MockitoExtension.class)
class MoveToCommandTest {

    @Test
    @DisplayName("should change queue executor state to `move to` state")
    void moveToCommandTest() {
        //arrange
        ThreadLocal<State> state = ThreadLocal.withInitial(NormalState::new);
        MoveToCommand moveToCommand = new MoveToCommand(state);
        moveToCommand.execute();
        Assertions.assertThat(state.get())
                .isInstanceOf(MoveToState.class);
    }
}