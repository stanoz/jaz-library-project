package com.library.tools.safeinvoker.repeaters;


public class Repeater implements IRepeater{
    String exceptionName;
    int counter;
    int retryCount;
    long delayTime;
    private final IRepeaterExceptionRegistry exceptionRegistry;

    public Repeater(IRepeaterExceptionRegistry exceptionRegistry) {
        this.exceptionRegistry = exceptionRegistry;
    }

    @Override
    public IRepeater For(Throwable exception) {
        var exname = exception.getClass().getName();
        if(exceptionName!=null && exceptionName.equalsIgnoreCase(exname)) return this;
        var entry = exceptionRegistry.EntryFor(exception);
        exceptionName= entry.exceptionName();
        counter=0;
        retryCount=entry.retriesCount();
        delayTime= entry.delay();
        return this;
    }

    @Override
    public void retry() {
        counter++;
    }

    @Override
    public boolean shouldRetry() {
        return counter<=retryCount;
    }

    @Override
    public IRepeater waiting() {
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }
}
