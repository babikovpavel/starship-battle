package ru.otus.homework7.ioc;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.otus.homework7.model.Movable;
import ru.otus.homework7.model.UObject;

import static org.junit.jupiter.api.Assertions.assertAll;

class IoCTest {

    @Mock
    private UObject uObject;

    @Test
    @DisplayName("Should generate new adaptor")
    void resolveMovableAdapterTest() {
        var adapter = IoC.resolve("Adapter", Movable.class.getName(), uObject);
        assertAll(() -> {
            Assertions.assertThat(adapter)
                    .as("should generate new movable adapter")
                    .isNotNull()
                    .isInstanceOf(Movable.class)
                    .hasFieldOrProperty("uObject");
            Assertions.assertThat(adapter.getClass())
                    .as("adapter has 4 methods of movable")
                    .matches(aClass -> aClass.getDeclaredMethods().length == 4);
        });
    }
}