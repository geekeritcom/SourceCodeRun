package com.geekerit.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
public class ScheduleTaskThrowErrorTest {


    private AtomicInteger atomicInteger;

    @BeforeEach
    public void before() {
        atomicInteger = new AtomicInteger(0);
    }

    @Test
    public void testTaskThrowUnExpectedError() throws InterruptedException {
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("errorTest-%d").build();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1, factory);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                int i = atomicInteger.incrementAndGet();
                log.info("task execute result is {}", i);
                if (i == 2) {
                    throw new Error();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 1, 1, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(10);

        assertEquals(atomicInteger.get(), 2);

        scheduledExecutorService.execute(() -> {
            log.info("execute new task...");
        });
    }

    String mockAppThrowError = "mock app throw error";


    @Test
    public void testTaskThrowAnyError() throws InterruptedException {
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("errorTest-%d").build();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1, factory);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                int i = atomicInteger.incrementAndGet();
                log.info("task execute result is {}", i);
                if (i == 2) {
                    throw new Error(mockAppThrowError);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (Throwable unExpected) {
                log.error("app throw unexpected error", unExpected);
                assertEquals(unExpected.getMessage(), mockAppThrowError);
            }
        }, 1, 1, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(10);



    }

}
