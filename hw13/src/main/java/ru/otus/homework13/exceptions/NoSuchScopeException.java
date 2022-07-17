package ru.otus.homework13.exceptions;

public class NoSuchScopeException extends RuntimeException {

    public NoSuchScopeException(String message) {
        super(message);
    }

    public NoSuchScopeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
