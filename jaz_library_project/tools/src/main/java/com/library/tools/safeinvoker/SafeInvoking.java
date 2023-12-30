package com.library.tools.safeinvoker;

import java.util.function.Consumer;

public interface SafeInvoking {
    InvokerResult SafeInvoke(NotSafeAction action);

    record InvokerResult(Exception exception, boolean isSuccess){

        public static InvokerResult Success(){
            return new InvokerResult(null, true);
        }

        public static InvokerResult FailedWithException(Exception exception){
            return new InvokerResult(exception, false);
        }

        public void onUnhandledException(Consumer<Exception> consumer){
            if(isSuccess)return;
            consumer.accept(exception);
        }
    }
}
