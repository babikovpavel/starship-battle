package ru.otus.homework7.adapters;

import ru.otus.homework7.model.UObject;

public interface AdaptersGenerator<T> {

    T generate(String interfaceName, UObject uObject);
}
