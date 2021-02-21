package acmr.javacore.basic.thread;

public class ThreadSafe {
    private static int count = 0;
    private static void increase() {
        for(int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "-" + count++);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(ThreadSafe::increase);
        Thread thread2 = new Thread(ThreadSafe::increase);
        Thread thread3 = new Thread(ThreadSafe::increase);
        thread1.start();
        thread2.start();
        thread3.start();

        while (thread1.isAlive() || thread2.isAlive() || thread3.isAlive()) {
            Thread.sleep(1000);
        }
        System.out.println(count);
    }
}
