package acmr.javacore.basic.collection;

import acmr.springframework.annotation.entity.Rat;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class QueueTest {
    public static void main(String[] args) {
        Queue<Rat> queue = new LinkedList<>();
        new Thread(()->{
            while (true) {
                queue.offer(new Rat());
                System.out.println("洞里又出来一只耗子，屋里现在有" + queue.size() + "只");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            while (true) {
                Rat rat = queue.poll();
                if(rat == null) {
                    System.out.println("耗子在哪儿");
                } else {
                    System.out.println("抓到一只耗子");
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

//        queue.forEach((e)->System.out.println(e));
    }
}
