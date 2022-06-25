package ru.otus.homework3.holders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CsvStrategiesHolder implements StrategiesHolder {

    private final Map<String, String> handleStrategies = new HashMap<>();

    public CsvStrategiesHolder(String source) throws IOException {
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(source);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] s = line.split(",");
                handleStrategies.put(s[0], s[1]);
            }
        }
    }

    @Override
    public String getHandlerClassByHash(String hash) {
        return handleStrategies.get(hash);
    }
}
