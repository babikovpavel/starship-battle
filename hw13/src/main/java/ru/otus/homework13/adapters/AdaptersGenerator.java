package ru.otus.homework13.adapters;

import ru.otus.homework13.model.UObject;

public interface AdaptersGenerator<T> {

    T generate(String interfaceName, UObject uObject);
}
