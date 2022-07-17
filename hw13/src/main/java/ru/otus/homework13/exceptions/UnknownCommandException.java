package ru.otus.homework13.exceptions;

public class UnknownCommandException extends RuntimeException {
    public UnknownCommandException(String message) {
        super(message);
    }

    public UnknownCommandException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
