package acmr.javacore.basic.thread;

import org.omg.CORBA.TIMEOUT;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author guoguo
 * @version 1.0.0
 * @date 2022/6/22
 * @description
 */
public class LockTest {
    private static class Test {
        int id;
        String name;
    }

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        System.out.println("加锁前。。。");
        System.out.println(ClassLayout.parseInstance(test).toPrintable());
        new Thread(() -> {
            synchronized (test) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //偏向锁4秒钟后生效
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Thread 抢占锁后。。。");
        System.out.println(ClassLayout.parseInstance(test).toPrintable());
        synchronized (test) {
            System.out.println("加锁后。。。");
            System.out.println(ClassLayout.parseInstance(test).toPrintable());
        }
    }
}
