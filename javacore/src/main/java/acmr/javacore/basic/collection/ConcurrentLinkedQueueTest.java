package acmr.javacore.basic.collection;

import acmr.springframework.annotation.entity.Rat;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueTest {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<Rat> ratQue = new ConcurrentLinkedQueue<>();
        ratQue.offer(null);
    }
}
