package ru.otus.homework3.commands;

import lombok.Getter;

public class Repeat implements Command {

    @Getter
    private final Command command;

    public Repeat(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.execute();
    }

    @Override
    public String getName() {
        return "Command";
    }
}
