package ru.otus.homework6.adapters;

import ru.otus.homework6.model.UObject;

public interface AdaptersGenerator<T> {

    T generate(String interfaceName, UObject uObject);
}
