package ru.otus.homework7.utils;

import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class TestUtils {

    public static Set<Thread> getCurrentThreads() {
        return Thread.getAllStackTraces().keySet();
    }
}
