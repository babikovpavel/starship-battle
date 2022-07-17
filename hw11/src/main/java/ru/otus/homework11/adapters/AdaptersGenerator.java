package ru.otus.homework11.adapters;

import ru.otus.homework11.model.UObject;

public interface AdaptersGenerator<T> {

    T generate(String interfaceName, UObject uObject);
}
