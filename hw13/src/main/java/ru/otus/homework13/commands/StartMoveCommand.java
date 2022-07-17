package ru.otus.homework13.commands;

import lombok.Data;
import ru.otus.homework13.model.UObject;

@Data
public class StartMoveCommand implements Command {

    private final UObject uObject;
    private final Integer startVelocity;

    @Override
    public void execute() {

    }
}
