package com.library.tools.safeinvoker;

import com.library.tools.safeinvoker.repeaters.IRepeater;
import com.library.tools.safeinvoker.repeaters.Repeater;
import com.library.tools.safeinvoker.repeaters.RepeaterExceptionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SafeInvokerConfiguration {

    @Bean
    public RepeaterExceptionRegistry getRegistry(){
        return RepeaterExceptionRegistry.getInstance();
    }

    @Bean
    @Scope("prototype")
    public Repeater repeater(RepeaterExceptionRegistry registry){
        return new Repeater(registry);
    }

    @Bean
    @Scope("prototype")
    public SafeInvoker safeInvoker(IRepeater repeater){
        return new SafeInvoker(repeater);
    }
}
