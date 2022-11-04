package ru.job4j.thread;

public final class DCLSingleton {

    private static DCLSingleton inst;

    public static DCLSingleton instOf() {
        synchronized (DCLSingleton.class) {
            if (inst == null) {
                inst = new DCLSingleton();
            }
        }
        return inst;
    }

    private DCLSingleton() {
    }

}
