package com.geekerit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ScheduleTaskTest {

    @Scheduled(initialDelay = 1000, fixedDelay = 3000)
    public void scheduleTask1() {
        log.info("schedule task1 start execute");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("schedule task1 end execute");
    }

    @Scheduled(initialDelay = 2000, fixedDelay = 1000)
    public void scheduleTask2() {
        log.info("schedule task2 start execute");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("schedule task2 end execute");
    }

}
