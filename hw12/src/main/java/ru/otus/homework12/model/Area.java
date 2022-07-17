package ru.otus.homework12.model;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

@Data
public class Area {

    private Vector<Integer> coords;

    private List<AreaMoveable> objects = new LinkedList<>();
}
