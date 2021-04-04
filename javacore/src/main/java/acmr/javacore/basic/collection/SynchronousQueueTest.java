package acmr.javacore.basic.collection;

import acmr.springframework.annotation.entity.Rat;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueTest {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Rat> ratQue = new SynchronousQueue<>();
        new Thread(() -> {
            Rat rat = new Rat();
            rat.setName("耗子--" + Thread.currentThread().getName());
            try {
                System.out.println(rat.getName() + "出洞了");
                ratQue.put(rat);
                System.out.println(rat.getName() + "被逮着了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        TimeUnit.SECONDS.sleep(10);
        System.out.println("屋里有老鼠了，快叫黑猫警长");
        new Thread(() -> {
            try {
                Rat rat = ratQue.take();
                System.out.println("黑猫警长真厉害，一出手就把[" + rat.getName() + "]给逮着了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
