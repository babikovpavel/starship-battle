package ru.otus.homework11.commands;

import lombok.RequiredArgsConstructor;
import ru.otus.homework11.state.MoveToState;
import ru.otus.homework11.state.State;

@RequiredArgsConstructor
public class MoveToCommand implements Command {

    private final ThreadLocal<State> state;

    @Override
    public void execute() {
        state.set(new MoveToState());
    }
}
