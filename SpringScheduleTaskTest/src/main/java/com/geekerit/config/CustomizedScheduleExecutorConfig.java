package com.geekerit.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

@Configuration
public class CustomizedScheduleExecutorConfig {

    @Bean(destroyMethod = "shutdown")
    public ScheduledExecutorService executorService() {
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("sBeanPool-%d").build();
        return Executors.newScheduledThreadPool(3, factory);
    }
}
