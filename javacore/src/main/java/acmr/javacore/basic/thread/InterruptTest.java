package acmr.javacore.basic.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author guoguo
 * @version 1.0.0
 * @date 2022/6/19
 * @description
 */
public class InterruptTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Thread1 running --- " + i++);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
        t1.start();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
    }
}
