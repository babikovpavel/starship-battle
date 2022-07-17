package ru.otus.homework11.commands;


import ru.otus.homework11.state.State;

public class HardStopCommand implements Command {

    private State state;

    public HardStopCommand(State state) {
        this.state = state;
    }

    @Override
    public void execute() {
        state = null;
    }
}
