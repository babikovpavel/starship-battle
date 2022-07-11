package ru.otus.homework7.commands;

import java.util.Queue;

public class SoftStopCommand implements Command {

    private final ThreadLocal<Queue<Command>> commands;
    private final ThreadLocal<Boolean> continueCondition;

    public SoftStopCommand(ThreadLocal<Queue<Command>> commands,
                           ThreadLocal<Boolean> continueCondition) {
        this.commands = commands;
        this.continueCondition = continueCondition;
    }

    @Override
    public void execute() {
        commands.get().add(new HardStopCommand(continueCondition));
    }
}
