package ru.otus.homework7.commands;


public class HardStopCommand implements Command {

    private final ThreadLocal<Boolean> continueCondition;

    public HardStopCommand(ThreadLocal<Boolean> continueCondition) {
        this.continueCondition = continueCondition;
    }

    @Override
    public void execute() {
        continueCondition.set(Boolean.FALSE);
    }
}
