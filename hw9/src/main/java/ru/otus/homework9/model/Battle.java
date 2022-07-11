package ru.otus.homework9.model;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

@Getter
public class Battle {

    private final String id;
    private final List<String> usersList;
    private final Queue<Command> commands;

    public Battle(List<String> usersList) {
        id = UUID.randomUUID().toString();
        this.usersList = usersList;
        this.commands = new LinkedList<>();
    }
}
