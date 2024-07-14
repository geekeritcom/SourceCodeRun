package com.geekerit.juc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadPoolExecutorUsageTest {

    ExecutorService executorService = Executors.newFixedThreadPool(1);


    @Test
    public void testTaskNotCheck() {
        int i = 10;
        for (int j = 0; j < i; j++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                int e = 1 / 0;
            });

        }
    }


    @Test
    public void testTaskCheck() {
        int i = 10;
        for (int j = 0; j < i; j++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    int e = 1 / 0;
                } catch (Exception ex) {
                    log.warn("task throw unexpected exception", ex);
                }
            });

        }
    }


}
