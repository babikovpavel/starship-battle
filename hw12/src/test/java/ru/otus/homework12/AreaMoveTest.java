package ru.otus.homework12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework12.commands.AreaMove;
import ru.otus.homework12.commands.CheckCollission;
import ru.otus.homework12.commands.Command;
import ru.otus.homework12.ioc.IoC;
import ru.otus.homework12.model.Area;
import ru.otus.homework12.model.AreaMoveable;
import ru.otus.homework12.model.SampleAreaMovable;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AreaMoveTest {

    @Mock
    private AreaMoveable areaMoveable;

    @Mock
    private IoC ioc;

    private Queue<Command> commands;

    @BeforeEach
    public void init() {
        commands = new LinkedList<>();
        when(ioc.resolve(eq("Commands")))
                .thenReturn(commands);
        when(areaMoveable.getArea())
                .thenReturn(new Area());
    }

    @Test
    @DisplayName("should move object to new area and add check collisions commands to queue")
    void doAreaMoveTest() {
        //arrange
        Area newArea = new Area();
        newArea.setObjects(new LinkedList<>(List.of(new SampleAreaMovable(), new SampleAreaMovable())));
        when(ioc.resolve(eq("Area.Get"), any()))
                .thenReturn(newArea);
        Vector<Integer> startPosition = new Vector<>();
        startPosition.add(12);
        startPosition.add(5);
        when(areaMoveable.getPosition()).thenReturn(startPosition);

        Vector<Double> velocity = new Vector<>();
        velocity.add(-7d);
        velocity.add(3d);
        when(areaMoveable.getVelocity()).thenReturn(velocity);
        //act
        AreaMove areaMove = new AreaMove(areaMoveable, ioc);
        areaMove.execute();
        //assert
        verify(areaMoveable, times(1))
                .setArea(newArea);
        assertThat(commands)
                .as("should contain new check collision commands")
                .hasSize(3)
                .hasOnlyElementsOfType(CheckCollission.class);
    }

}
