package ru.otus.homework13.commands;

import lombok.Data;
import ru.otus.homework13.model.UObject;

@Data
public class ShootCommand implements Command {

    private final UObject uObject;

    @Override
    public void execute() {

    }
}
