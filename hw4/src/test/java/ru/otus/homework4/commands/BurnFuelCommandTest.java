package ru.otus.homework4.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework4.model.FuelDriven;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BurnFuelCommandTest {

    @Mock
    private FuelDriven fuelDriven;

    @Test
    @DisplayName("should burn fuel of object")
    void burnFuelTest() {
        //act
        BurnFuelCommand burnFuelCommand = new BurnFuelCommand(fuelDriven);
        burnFuelCommand.execute();
        //assert
        verify(fuelDriven, times(1)).burnFuel();
    }
}