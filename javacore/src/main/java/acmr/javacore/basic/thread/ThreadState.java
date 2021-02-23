package acmr.javacore.basic.thread;

public class ThreadState {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "-2-" + Thread.currentThread().getState());
            synchronized (ThreadSafe.class) {
                System.out.println(Thread.currentThread().getName() + "-4-" + Thread.currentThread().getState());
            }
            while (true) {
                synchronized (lock) {
                    try {
                        lock.wait();
                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName() + "-7-" + Thread.currentThread().getState());
                        break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.out.println(thread.getName() + "-1-" + thread.getState());
        thread.start();
        synchronized (ThreadSafe.class) {
            Thread.sleep(1000);
            System.out.println(thread.getName() + "-3-" + thread.getState());
        }
        Thread.sleep(1000);
        System.out.println(thread.getName() + "-5-" + thread.getState());
        synchronized (lock) {
            lock.notify();
        }
        Thread.sleep(100);
        System.out.println(thread.getName() + "-6-" + thread.getState());
        Thread.sleep(4000);
        System.out.println(thread.getName() + "-8-" + thread.getState());
    }

}
