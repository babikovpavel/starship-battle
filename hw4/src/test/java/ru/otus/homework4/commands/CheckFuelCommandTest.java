package ru.otus.homework4.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework4.exceptions.CommandException;
import ru.otus.homework4.model.FuelDriven;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckFuelCommandTest {

    @Mock
    private FuelDriven fuelDriven;

    @ParameterizedTest
    @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
    @DisplayName("should throw exception if fuel quantity is equal or lower than zero")
    void executeWithZeroFuelTest(int fuelQuantity) {
        //arrange
        when(fuelDriven.getFuelAmount())
                .thenReturn(fuelQuantity);
        CheckFuelCommand checkFuelCommand = new CheckFuelCommand(fuelDriven);
        //act & assert
        assertThrows(CommandException.class, checkFuelCommand::execute, "object has no fuel");
        verify(fuelDriven, times(1)).getFuelAmount();
    }

    @Test
    @DisplayName("should do nothing if fuel quantity is greater than zero")
    void executeWithPositiveQuantityOfFuelTest() {
        //arrange
        when(fuelDriven.getFuelAmount())
                .thenReturn(100);
        when(fuelDriven.getFuelBurnSpeed())
                .thenReturn(1);
        CheckFuelCommand checkFuelCommand = new CheckFuelCommand(fuelDriven);
        //act & assert
        Assertions.assertDoesNotThrow(checkFuelCommand::execute, "everything is ok if we have fuel");
    }

}