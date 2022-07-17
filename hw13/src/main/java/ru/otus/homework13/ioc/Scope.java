package ru.otus.homework13.ioc;

import java.util.function.Function;

public interface Scope {

    void save(String name, Function<Object[], Object> strategy);

    Function<Object[], Object> get(String name);
}
