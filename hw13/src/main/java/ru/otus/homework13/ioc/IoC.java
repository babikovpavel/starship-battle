package ru.otus.homework13.ioc;

import lombok.experimental.UtilityClass;
import ru.otus.homework13.adapters.AdaptersGenerator;
import ru.otus.homework13.adapters.AdaptersGeneratorImpl;
import ru.otus.homework13.commands.Command;
import ru.otus.homework13.commands.ShootCommand;
import ru.otus.homework13.commands.StartMoveCommand;
import ru.otus.homework13.commands.StopMoveCommand;
import ru.otus.homework13.exceptions.NoSuchScopeException;
import ru.otus.homework13.interpretator.CommandsInterpreter;
import ru.otus.homework13.model.UObject;

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

    private final CommandsInterpreter interpreter =
            new CommandsInterpreter();

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
        strategies.put("Interpret", args -> interpreter.interpret((String) args[0]));
        //interpret commands
        strategies.put("StartMove", args -> new StartMoveCommand((UObject) IoC.resolve("Objects.Get", args[0]),
                Integer.valueOf((String) args[1])));
        strategies.put("StopMove", args -> new StopMoveCommand((UObject) IoC.resolve("Objects.Get", args[0])));
        strategies.put("Shoot", args -> new ShootCommand((UObject) IoC.resolve("Objects.Get", args[0])));

        return strategies;
    }
}
