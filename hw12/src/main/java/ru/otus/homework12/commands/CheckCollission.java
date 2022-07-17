package ru.otus.homework12.commands;

import lombok.RequiredArgsConstructor;
import ru.otus.homework12.model.AreaMoveable;

@RequiredArgsConstructor
public class CheckCollission implements Command {

    private final AreaMoveable firstObject;
    private final AreaMoveable secondObject;

    @Override
    public void execute() {
        //here logic to check collissions
    }
}
