package com.jacky.concurrency;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.ExecutionException;

public class TransformDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ListeningExecutorService executorService = MoreExecutors.newDirectExecutorService();
        ListenableFuture<String> future1 = executorService.submit(() -> "hello, guava");
        ListenableFuture<Integer> transform = Futures.transform(future1, String::length, executorService);
//        ListenableFuture<Integer> transform = Futures.transformAsync(future1, String::length, executorService);
        System.out.println(transform.get());


    }
}
