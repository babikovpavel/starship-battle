package ru.otus.homework3.executors;

import ru.otus.homework3.commands.Command;

public class CommonCommandExecutor implements CommandExecutor {

    @Override
    public void execute(Command command) {
        command.execute();
    }
}
