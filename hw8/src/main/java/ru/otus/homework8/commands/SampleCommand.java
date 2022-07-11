package ru.otus.homework8.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SampleCommand implements Command {

    private final int count;

    @Override
    public void execute() {
        throw new IllegalArgumentException();
    }
}
