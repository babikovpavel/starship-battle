package ru.otus.homework4.commands;

import ru.otus.homework4.model.FuelDriven;
import ru.otus.homework4.model.Movable;

import java.util.ArrayList;
import java.util.List;

public class MacroCommand implements Command {

    private final CommandsQueue commandsQueue;
    private final List<Command> commands;
    private final Movable movable;
    private final FuelDriven fuelDriven;

    public MacroCommand(CommandsQueue commandsQueue,
                        Movable movable,
                        FuelDriven fuelDriven) {
        this.commandsQueue = commandsQueue;
        this.movable = movable;
        this.fuelDriven = fuelDriven;
        commands = new ArrayList<>();
        commands.add(new CheckFuelCommand(fuelDriven));
        commands.add(new MoveCommand(movable));
        commands.add(new BurnFuelCommand(fuelDriven));
    }

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
        commandsQueue.append(new MacroCommand(commandsQueue, movable, fuelDriven));
    }
}
