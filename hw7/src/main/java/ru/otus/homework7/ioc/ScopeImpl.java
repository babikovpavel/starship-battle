package ru.otus.homework7.ioc;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
public class ScopeImpl implements Scope {

    private final Map<String, Function<Object[], Object>> strategies;

    @Override
    public void save(String name, Function<Object[], Object> strategy) {
        strategies.put(name, strategy);
    }

    @Override
    public Function<Object[], Object> get(String name) {
        return strategies.get(name);
    }
}
