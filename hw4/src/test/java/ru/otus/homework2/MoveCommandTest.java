package ru.otus.homework4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework4.commands.MoveCommand;
import ru.otus.homework4.model.Movable;

import java.util.Vector;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MoveCommandTest {

    @Mock
    private Movable movable;

    private MoveCommand moveCommand;

    @BeforeEach
    public void init() {
        this.moveCommand = new MoveCommand(movable);
    }

    @Test
    @DisplayName("Should return position (5, 8) for object with start coords (12, 5) and velocity (-7, 3)")
    void doMoveTest() {
        //arrange
        Vector<Integer> startPosition = new Vector<>();
        startPosition.add(12);
        startPosition.add(5);
        when(movable.getPosition()).thenReturn(startPosition);

        Vector<Double> velocity = new Vector<>();
        velocity.add(-7d);
        velocity.add(3d);
        when(movable.getVelocity()).thenReturn(velocity);

        Vector<Integer> expectedPosition = new Vector<>();
        expectedPosition.add(5);
        expectedPosition.add(8);

        //act
        moveCommand.execute();
        //assert
        verify(movable, times(1)).setPosition(expectedPosition);
    }

    @Test
    @DisplayName("Should throw for movable that do not return position")
    void moveObjectWithoutPositionTest() {
        //arrange
        Vector<Double> velocity = new Vector<>();
        velocity.add(-7d);
        velocity.add(3d);
        when(movable.getVelocity()).thenReturn(velocity);

        when(movable.getPosition()).thenReturn(null);

        //act & assert
        Assertions.assertThrows(RuntimeException.class, () -> moveCommand.execute());
    }

    @Test
    @DisplayName("Should throw for movable that do not return velocity")
    void moveObjectWithoutVelocityTest() {
        //arrange
        Vector<Integer> startPosition = new Vector<>();
        startPosition.add(12);
        startPosition.add(5);
        when(movable.getPosition()).thenReturn(startPosition);

        when(movable.getVelocity()).thenReturn(null);

        //act & assert
        Assertions.assertThrows(Exception.class, () -> moveCommand.execute());
    }

    @Test
    @DisplayName("Should throw for movable that couldn't update it's position")
    void moveObjectThatCantChangePosition() {
        //arrange
        Vector<Integer> startPosition = new Vector<>();
        startPosition.add(12);
        startPosition.add(5);
        when(movable.getPosition()).thenReturn(startPosition);

        Vector<Double> velocity = new Vector<>();
        velocity.add(-7d);
        velocity.add(3d);
        when(movable.getVelocity()).thenReturn(velocity);

        doThrow(RuntimeException.class).when(movable).setPosition(any());

        //act & assert
        Assertions.assertThrows(Exception.class, () -> moveCommand.execute());
    }

}
