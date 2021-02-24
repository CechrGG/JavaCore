package acmr.javacore.basic.thread;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition ganfan = lock.newCondition();
    private static final Condition zuofan = lock.newCondition();
    private static final LinkedList<String> fans= new LinkedList<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "没饭啊, 妈妈快给我做饭");
                zuofan.signal();
                ganfan.await();
                System.out.println(Thread.currentThread().getName() + "哎妈呀，这" + fans.poll() + "真香");
            } catch (InterruptedException e) {
                System.out.println("糟了，" + Thread.currentThread().getName() + " 我要饿死了");
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            lock.lock();
            try {
                zuofan.await();
                System.out.println(Thread.currentThread().getName() + "马上就来，别着急");
                TimeUnit.SECONDS.sleep(3);
                String fan = "红烧又";
                fans.offer(fan);
                System.out.println(fan + "做好了，快来干饭");
                ganfan.signal();
            } catch (Exception e) {
                System.out.println("糟了，" + Thread.currentThread().getName() + "有大事发生，你还是泡面吃吧");
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
