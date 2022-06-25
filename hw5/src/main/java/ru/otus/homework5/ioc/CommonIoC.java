package ru.otus.homework5.ioc;

import lombok.RequiredArgsConstructor;
import ru.otus.homework5.commands.Command;
import ru.otus.homework5.exceptions.NoSuchScopeException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public class CommonIoC implements IoC<Object> {

    public static final String DEFAULT_SCOPE_NAME = "root";

    private final ThreadLocal<Map<String, Scope>> scopes =
            ThreadLocal.withInitial(() -> new HashMap<>(Map.of(DEFAULT_SCOPE_NAME,
                    new ScopeImpl(prepareDefaultStrategies()))));

    private final ThreadLocal<String> currentScope =
            ThreadLocal.withInitial(() -> DEFAULT_SCOPE_NAME);

    public Object resolve(String key, Object... args) {
        Function<Object[], Object> objectFunction = scopes.get().get(currentScope.get()).get(key);
        return Optional.ofNullable(objectFunction).orElse((Object[] params) -> null).apply(args);
    }

    private HashMap<String, Function<Object[], Object>> prepareDefaultStrategies() {
        HashMap<String, Function<Object[], Object>> strategies = new HashMap<>();
        strategies.put("IoC.Register", args -> (Command) () -> scopes.get().get(currentScope.get())
                .save((String) args[0], (Function<Object[], Object>) args[1]));
        strategies.put("Scopes.New", args -> (Command) () -> scopes.get().put((String) args[0],
                new ScopeImpl(prepareDefaultStrategies())));
        strategies.put("Scopes.Current", args -> (Command) () -> {
            String scopeName = (String) args[0];
            if (scopes.get().get(scopeName) != null) {
                currentScope.set(scopeName);
            } else {
                throw new NoSuchScopeException("No scope with name " + scopeName);
            }
        });
        return strategies;
    }
}
