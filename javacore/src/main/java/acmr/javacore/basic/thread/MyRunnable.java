package acmr.javacore.basic.thread;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("我是实现Runnable接口的线程");
    }
}
