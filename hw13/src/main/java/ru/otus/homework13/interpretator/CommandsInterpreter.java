package ru.otus.homework13.interpretator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otus.homework13.commands.Command;
import ru.otus.homework13.exceptions.UnknownCommandException;
import ru.otus.homework13.ioc.IoC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandsInterpreter implements Interpretator<Command> {

    private static final String UNKNOWN_COMMAND_MESSAGE = "Неверный формат приказа";
    public static final String ACTION = "action";

    @Override
    public Command interpret(String source) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode newNode = objectMapper.readTree(source);
            if (newNode.has(ACTION)) {
                String action = newNode.get(ACTION).asText();
                List<Object> args = new ArrayList<>();
                newNode.fields().forEachRemaining(entry ->
                {
                    if (!Objects.equals(entry.getKey(), ACTION)) {
                        args.add(entry.getValue().textValue());
                    }
                });
                return (Command) IoC.resolve(action, args.toArray());
            }
        } catch (IOException ex) {
            throw new UnknownCommandException(UNKNOWN_COMMAND_MESSAGE, ex);
        }
        throw new UnknownCommandException(UNKNOWN_COMMAND_MESSAGE);
    }
}
