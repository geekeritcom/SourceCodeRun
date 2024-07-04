package com.geekerit.juc;

import java.util.concurrent.*;

public class ThreadPoolExecutorTest {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,
                1,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(1),
                new MyThreadFactory()
        );

        threadPoolExecutor.execute(() -> {
            System.out.println("this message will never print");
        });


    }


}

class MyThreadFactory implements ThreadFactory
{

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r);
    }
}
