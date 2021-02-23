package acmr.javacore.basic.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread myThread = new MyThread();
        MyRunnable myRunnable = new MyRunnable();
        Callable<String> myCallable = new MyCallable();
        FutureTask<String> task = new FutureTask<>(myCallable);
        myThread.start();
        new Thread(myRunnable).start();
        new Thread(task).start();
        System.out.println(task.get());
    }
}
