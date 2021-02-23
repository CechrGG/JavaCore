package acmr.javacore.basic.thread;

public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("我是继承Thread的线程");
    }
}
