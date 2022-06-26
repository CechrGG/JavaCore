package acmr.javacore.basic.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author guoguo
 * @version 1.0.0
 * @date 2022/6/26
 * @description
 */
public class ReentrantLockTest2 {
    public static ReentrantLock lock = new ReentrantLock();
    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for(int i = 0; i < 10000; i++) {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " 竞争上岗");
                    count++;
                } finally {
                    lock.unlock();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            for(int i = 0; i < 10000; i++) {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " 竞争上岗");
                    count++;
                } finally {
                    lock.unlock();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(count);
    }
}
