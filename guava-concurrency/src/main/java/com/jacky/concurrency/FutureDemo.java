package com.jacky.concurrency;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.*;

/**
 * jdk实现中(java 8之前)想要获取异步计算结果,可以通过Future + Callable方式实现
 */
public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 提交一个实现了Callable接口的task
        Future<Integer> future = executorService.submit(new DemoCallable(10, 20));
        // 这里获取异步计算结果的时候会一直阻塞,直到获取到结果或出现异常
        System.out.println(future.get());
        System.out.println("---- main thread ----");
        executorService.shutdown();
    }

}

class DemoCallable implements Callable<Integer> {
    private int a;
    private int b;

    public DemoCallable(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer call() throws Exception {
        return a * b;
    }
}