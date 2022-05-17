package ru.otus.homework3.commands;

public class FailRepeat extends Repeat {

    public FailRepeat(Command command) {
        super(command);
    }

    @Override
    public String getName() {
        return "FailRepeat";
    }
}
