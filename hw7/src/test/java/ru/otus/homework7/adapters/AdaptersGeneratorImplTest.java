package ru.otus.homework7.adapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.homework7.model.UObject;

import static org.junit.jupiter.api.Assertions.*;

class AdaptersGeneratorImplTest {

    private AdaptersGenerator<?> adaptersGenerator;

    private UObject uObject;

    @BeforeEach
    public void init() {
        adaptersGenerator = new AdaptersGeneratorImpl<>();
    }

    @Test
    void generateTest() {
        var adapter = adaptersGenerator.generate("ru.otus.homework7.model.Movable", uObject);
        var aClass = adapter.getClass();
        assertAll(() -> {
            assertEquals("ru.otus.homework7.model.MovableAdapter", aClass.getName(), "should have correct name");
            assertEquals(4, aClass.getDeclaredMethods().length, "should have 4 methods");
        });
    }

    @Test
    void generateTest_throwIfWrongType() {
        assertThrows(ClassNotFoundException.class, () -> adaptersGenerator.generate("Movable", uObject),
                "should throw if no such interface found");
    }
}