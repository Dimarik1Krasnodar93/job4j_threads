package ru.job4j.cache;

import java.util.Optional;

public class OptimisticException extends RuntimeException {
    public OptimisticException(String text)  {
        super(text);
    }
}
