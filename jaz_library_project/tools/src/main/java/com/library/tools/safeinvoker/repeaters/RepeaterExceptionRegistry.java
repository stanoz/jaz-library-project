package com.library.tools.safeinvoker.repeaters;

import java.util.HashMap;
import java.util.Map;

public class RepeaterExceptionRegistry  implements IRepeaterExceptionRegistry{
    private final static RepeaterExceptionRegistry instance;
    static{
        instance = new RepeaterExceptionRegistry();
    }

    public static RepeaterExceptionRegistry getInstance(){ return instance; }

    private final Map<String, RegistryEntry> registry = new HashMap<>();

    private RepeaterExceptionRegistry(){}

    @Override
    public void add(Class<? extends Throwable> exceptionClass, int retries, long delay) {
        var exceptionName = exceptionClass.getName();
        this.registry.put(exceptionName, new RegistryEntry(exceptionName, delay, retries));
    }

    @Override
    public  RegistryEntry EntryFor(Throwable exception) {
        return registry.getOrDefault(exception.getClass().getName(),RegistryEntry.Default(exception));
    }
}
