package ru.otus.homework4.commands;

import ru.otus.homework4.model.FuelDriven;

public class BurnFuelCommand implements Command {

    private final FuelDriven fuelDrivenObject;

    public BurnFuelCommand(FuelDriven fuelDrivenObject) {
        this.fuelDrivenObject = fuelDrivenObject;
    }

    @Override
    public void execute() {
        fuelDrivenObject.burnFuel();
    }
}
