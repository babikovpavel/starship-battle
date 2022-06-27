package ru.otus.homework5.ioc;

public interface IoC<T> {

    T resolve(String key, Object... args);
}
