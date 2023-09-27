package com.jacky.concurrency;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;

import java.util.concurrent.ExecutionException;

/**
 * SettableFuture不需要计算返回值, 只需求指定返回值的类型即可
 * set或者setException来设置值或者异常信息
 */
public class SettableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SettableFuture<Integer> settableFuture = SettableFuture.create();

        ListeningExecutorService executorService = MoreExecutors.newDirectExecutorService();
        ListenableFuture<Integer> future = executorService.submit(() -> {
            // 这里给SettableFuture set值后, get操作才能获取到值, 否则就会一直阻塞获取超时
            return 5;
        });

        System.out.println(settableFuture.get());
        System.out.println(future.get());

    }

}
