package ru.otus.homework5.ioc;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.otus.homework5.commands.Command;
import ru.otus.homework5.commands.FastMove;
import ru.otus.homework5.commands.Move;
import ru.otus.homework5.commands.SlowMove;
import ru.otus.homework5.exceptions.NoSuchScopeException;
import ru.otus.homework5.model.Movable;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class CommonIoCTest {

    @Mock
    private Movable movable;

    private IoC<Object> IoC;

    @BeforeEach
    public void init() {
        this.IoC = new CommonIoC();
    }

    @Test
    @DisplayName("Should create object with registered strategy")
    void resolveCommandTest() {
        //arrange
        ((Command) IoC.resolve("IoC.Register", "Move", (Function<Object[], Move>) o -> new SlowMove(movable)))
                .execute();
        //act
        Command move = (Command) IoC.resolve("Move");
        //assert
        Assertions.assertThat(move)
                .as("Move should not be null")
                .isNotNull()
                .isInstanceOf(SlowMove.class);
    }

    @Test
    @DisplayName("Should rewrite strategy if same name")
    void rewriteCommandTest() {
        //arrange
        ((Command) IoC.resolve("IoC.Register", "Move", (Function<Object[], Move>) o -> new SlowMove(movable)))
                .execute();
        ((Command) IoC.resolve("IoC.Register", "Move", (Function<Object[], Move>) o -> new FastMove(movable)))
                .execute();
        //act
        Command move = (Command) IoC.resolve("Move");
        //assert
        Assertions.assertThat(move)
                .as("Should rewrite command because of same scope")
                .isNotNull()
                .isInstanceOf(FastMove.class);
    }

    @Test
    @DisplayName("Should not return any object if strategy registered in another scope")
    void differentScopesTest() {
        //arrange
        ((Command) IoC.resolve("IoC.Register", "Move",
                (Function<Object[], Move>) o -> new SlowMove(movable))).execute();
        ((Command) IoC.resolve("Scopes.New", "Game2")).execute();
        ((Command) IoC.resolve("Scopes.Current", "Game2")).execute();
        //act
        Command move = (Command) IoC.resolve("Move");
        //assert
        Assertions.assertThat(move)
                .as("Should return null because of switched scope")
                .isNull();
    }

    @Test
    @DisplayName("Every thread should have their own scopes and strategies")
    void differentThreadTest() {
        //arrange
        ((Command) IoC.resolve("IoC.Register", "Move",
                (Function<Object[], Move>) o -> new SlowMove(movable))).execute();
        Thread t1 = new Thread(() -> ((Command) IoC.resolve("IoC.Register", "Move",
                (Function<Object[], Move>) o -> new FastMove(movable))).execute());
        t1.start();
        //act
        Command move = (Command) IoC.resolve("Move");
        //assert
        Assertions.assertThat(move)
                .as("Should return slow move because of another thread")
                .isNotNull()
                .isInstanceOf(SlowMove.class);
    }

    @Test
    @DisplayName("Should throw if no scope with name defined")
    void unknownScopeTest() {
        //act
        Command command = (Command) IoC.resolve("Scopes.Current", "unknown");
        assertThrows(NoSuchScopeException.class, command::execute);
    }

}