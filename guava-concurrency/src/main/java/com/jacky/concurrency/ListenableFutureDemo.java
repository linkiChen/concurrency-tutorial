package com.jacky.concurrency;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class ListenableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ListeningExecutorService executorService = MoreExecutors.newDirectExecutorService();
        ListenableFuture<Long> future = executorService.submit(new DemoListenableFutureCallable(5, 9));

//        System.out.println(future.get());
//        // 给ListenableFuture添加一个监听器, 在future执行完之后, 监听器就会执行
//        future.addListener(() -> {
//            System.out.println("---------------");
//        }, executorService);

        // 给ListenableFuture添加一个回调, 当任务执行成功/异常后会调用成功或失败回调函数
        Futures.addCallback(future, new FutureCallback<Long>() {
            @Override
            public void onSuccess(Long result) {
                System.out.println("成功回调,任务执行结果:" + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("失败回调");
                t.printStackTrace();
            }
        }, executorService);
    }

}

class DemoListenableFutureCallable implements Callable<Long> {
    private long a;
    private long b;

    public DemoListenableFutureCallable(long a, long b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Long call() throws Exception {
        System.out.println("执行Callable call");
        return a + b;
    }
}
