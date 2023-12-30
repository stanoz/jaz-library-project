package com.library.tools.safeinvoker;


import com.library.tools.safeinvoker.repeaters.IRepeater;

public class SafeInvoker implements SafeInvoking{
    private final IRepeater repeater;

    public SafeInvoker(IRepeater repeater) {
        this.repeater = repeater;
    }


    @Override
    public InvokerResult SafeInvoke(NotSafeAction action) {
        try{
            return tryToExecute(action);
        }
        catch(Exception ex){
            return repeatExecution(action, ex);
        }
    }

    private InvokerResult repeatExecution(NotSafeAction action, Exception ex) {
        InvokerResult result = tryToExecuteAgain(action, ex);
        while(repeater.shouldRetry() || !result.isSuccess())
        {
            result = tryToExecuteAgain(action, result.exception());
        }
        return result;
    }

    private InvokerResult tryToExecuteAgain(NotSafeAction action, Exception ex) {
        repeater.For(ex).waiting();
        if(!repeater.shouldRetry()) return InvokerResult.FailedWithException(ex);
        try{
            return tryToExecute(action);
        }catch(Exception ex2){
            repeater.For(ex2).waiting().retry();
            if(!repeater.shouldRetry()) return InvokerResult.FailedWithException(ex2);
        }
        return InvokerResult.FailedWithException(ex);
    }

    private static InvokerResult tryToExecute(NotSafeAction action) throws Exception {
        action.execute();
        return InvokerResult.Success();
    }
}
