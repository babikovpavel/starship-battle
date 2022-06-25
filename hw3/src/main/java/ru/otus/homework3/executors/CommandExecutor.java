package ru.otus.homework3.executors;

import ru.otus.homework3.commands.Command;

public interface CommandExecutor {

    void execute(Command command);
}
