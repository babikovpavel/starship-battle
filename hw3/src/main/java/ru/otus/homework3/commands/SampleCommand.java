package ru.otus.homework3.commands;

public class SampleCommand implements Command {
    @Override
    public void execute() {
        throw new IllegalArgumentException();
    }

    @Override
    public String getName() {
        return "SampleCommand";
    }
}
