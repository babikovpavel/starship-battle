package ru.otus.homework4.commands;

import lombok.SneakyThrows;
import ru.otus.homework4.exceptions.CommandException;
import ru.otus.homework4.model.FuelDriven;

public class CheckFuelCommand implements Command {

    private final FuelDriven fuelDrivenObject;

    public CheckFuelCommand(FuelDriven fuelDrivenObject) {
        this.fuelDrivenObject = fuelDrivenObject;
    }

    @Override
    @SneakyThrows
    public void execute() {
        int fuelAmount = fuelDrivenObject.getFuelAmount();
        if (fuelAmount <= 0 || fuelAmount < fuelDrivenObject.getFuelBurnSpeed()) {
            throw new CommandException("Not enough fuel");
        }
    }
}
