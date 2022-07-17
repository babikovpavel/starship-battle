package ru.otus.homework11.ioc;

import lombok.experimental.UtilityClass;
import ru.otus.homework11.adapters.AdaptersGenerator;
import ru.otus.homework11.adapters.AdaptersGeneratorImpl;
import ru.otus.homework11.commands.Command;
import ru.otus.homework11.exceptions.NoSuchScopeException;
import ru.otus.homework11.handlers.ExceptionHandler;
import ru.otus.homework11.model.UObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@UtilityClass
public class IoC {

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
        strategies.put("Adapter.Generator", args -> new AdaptersGeneratorImpl<>());
        strategies.put("Adapter", args -> ((AdaptersGenerator<?>) IoC.resolve("Adapter.Generator"))
                .generate((String) args[0], (UObject) args[1]));
        strategies.put("ExceptionHandler", args -> (ExceptionHandler) (command, exception) -> {
        });
        return strategies;
    }
}
