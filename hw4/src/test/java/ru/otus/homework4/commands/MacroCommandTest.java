package ru.otus.homework4.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework4.exceptions.CommandException;
import ru.otus.homework4.model.FuelDriven;
import ru.otus.homework4.model.Movable;

import java.util.List;
import java.util.Vector;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MacroCommandTest {

    @Mock
    private CommandsQueue commandsQueue;

    @Mock
    private Movable movable;

    @Mock
    private FuelDriven fuelDriven;

    @Test
    @DisplayName("should check for fuel, move object, burn fuel and add another macro command")
    void executeMacroCommandTest() {
        //arrange
        Vector<Integer> position = new Vector<>(List.of(1, 1));
        Vector<Double> velocity = new Vector<>(List.of(1d, 1d));
        when(fuelDriven.getFuelAmount())
                .thenReturn(100);
        when(fuelDriven.getFuelBurnSpeed())
                .thenReturn(10);
        when(movable.getPosition())
                .thenReturn(position);
        when(movable.getVelocity())
                .thenReturn(velocity);
        //act
        MacroCommand macroCommand = new MacroCommand(commandsQueue, movable, fuelDriven);
        macroCommand.execute();
        //assert
        Assertions.assertAll(() -> {
            verify(fuelDriven, times(1)).getFuelAmount();
            verify(fuelDriven, times(1)).getFuelBurnSpeed();
            verify(movable, times(1)).getPosition();
            verify(movable, times(1)).getVelocity();
            verify(movable, times(1)).setPosition(any());
            verify(fuelDriven, times(1)).burnFuel();
            verify(commandsQueue, times(1)).append(any(MacroCommand.class));
        });
    }

    @Test
    @DisplayName("should not execute move if not enough fuel")
    void executeMacroCommandWithoutFuelTest() {
        when(fuelDriven.getFuelAmount())
                .thenReturn(0);
        MacroCommand macroCommand = new MacroCommand(commandsQueue, movable, fuelDriven);
        Assertions.assertThrows(CommandException.class, macroCommand::execute, "can't move without fuel");
    }
}