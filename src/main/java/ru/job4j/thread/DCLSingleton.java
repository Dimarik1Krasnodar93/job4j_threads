package ru.job4j.thread;

public final class DCLSingleton {

    private static volatile DCLSingleton inst;

    public static synchronized DCLSingleton instOf() {
        if (inst == null) {
            inst = new DCLSingleton();
        }
        return inst;
    }

    private DCLSingleton() {
    }

}
