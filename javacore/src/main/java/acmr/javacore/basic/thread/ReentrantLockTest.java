package acmr.javacore.basic.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "捡到锁了, 等等看");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "等了这么半天也没人来");
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + "谁爱要谁要");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    System.out.println("糟了，" + Thread.currentThread().getName() + " 被打断了");
                    e.printStackTrace();
                    break;
                }
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
                while (true) {
                    if (lock.tryLock(1, TimeUnit.SECONDS)) {
                        System.out.println(Thread.currentThread().getName() + "捡到锁了");
                    }
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "等了这么半天也没人来");
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                        System.out.println(Thread.currentThread().getName() + "谁爱要谁要");
                        TimeUnit.SECONDS.sleep(3);
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("糟了，" + Thread.currentThread().getName() + "被打断了");
                e.printStackTrace();
            }
        }).start();
    }
}
