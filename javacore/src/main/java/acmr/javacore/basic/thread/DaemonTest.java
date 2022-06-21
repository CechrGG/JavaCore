package acmr.javacore.basic.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author guoguo
 * @version 1.0.0
 * @date 2022/6/21
 * @description
 */
public class DaemonTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Running..." + i++);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("thread over");
        });
        thread.setDaemon(true);
        thread.start();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("main over");
    }
}
