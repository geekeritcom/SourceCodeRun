package com.geekerit.juc.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolScheduleAPITest {

    private ScheduledExecutorService scheduledExecutorService;

    @BeforeEach
    public void before() {
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
    }

    @Test
    public void testScheduleWithFixRate() throws InterruptedException {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                log.info("start task execute by scheduleAtFixedRate");
                TimeUnit.SECONDS.sleep(5);
                log.info("task execute finished by scheduleAtFixedRate");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 1, 2, TimeUnit.SECONDS);

        Thread.currentThread().join();
    }

    @Test
    public void testScheduleWithFixDelay() throws InterruptedException {
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                log.info("start task execute by scheduleWithFixedDelay");
                TimeUnit.SECONDS.sleep(5);
                log.info("task execute finished by scheduleWithFixedDelay");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 1, 2, TimeUnit.SECONDS);

        Thread.currentThread().join();
    }
}
