package acmr.javacore.basic.collection;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
    public static void main(String[] args) {
        Queue queue = new LinkedList();
        queue.offer("abc");
        queue.offer(null);
        queue.offer(123);
        queue.offer(null);
        queue.offer(new String("ABC"));
        queue.forEach((e)->System.out.println(e));
    }
}
