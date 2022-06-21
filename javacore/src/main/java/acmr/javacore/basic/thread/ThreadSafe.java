package acmr.javacore.basic.thread;

import org.openjdk.jol.info.ClassLayout;

public class ThreadSafe {
    private static int count = 0;
    private static void increase() {
        for(int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + "-" + count++);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        long l = System.currentTimeMillis();
        Thread thread1 = new Thread(ThreadSafe::increase);
        Thread thread2 = new Thread(ThreadSafe::increase);
        Thread thread3 = new Thread(ThreadSafe::increase);
        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println(count);
        System.out.println(System.currentTimeMillis() - l);
        System.out.println(ClassLayout.parseInstance(thread1).toPrintable());
    }
}
