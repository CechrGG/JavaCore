package acmr.javacore.basic.collection;

import acmr.springframework.annotation.entity.Rat;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<Rat> ratQue = new PriorityQueue<>(Comparator.comparing(Rat::getWeight));
        for(int i = 0; i < 10; i++) {
            Rat rat = new Rat();
            rat.setName("耗子" + i + "号");
            rat.setWeight(BigDecimal.valueOf(10 - i + Math.random()).setScale(2, BigDecimal.ROUND_HALF_UP));
            ratQue.offer(rat);
        }
        while (!ratQue.isEmpty()) {
            Rat rat = ratQue.poll();
            System.out.println(rat.getName() + "-" + rat.getWeight());
        }
    }

}
