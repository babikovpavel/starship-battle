package ru.otus.homework8.endpoints;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Message {
    private final long gameId;
    private final long objectId;
    private final long operationId;
    private final Object[] args;
}
